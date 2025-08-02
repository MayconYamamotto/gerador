package br.com.gerador.generator;

import br.com.gerador.model.EntityModel;
import br.com.gerador.model.FieldModel;
import br.com.gerador.model.ValidationModel;
import br.com.gerador.util.TypeMapper;

import java.util.HashSet;
import java.util.Set;

import static br.com.gerador.util.StringUtils.convertToSnakeCase;

public class JavaEntityGenerator {

  public String generateEntity(EntityModel entity) {
    StringBuilder sb = new StringBuilder();

    // Package declaration
    sb.append("package ").append(entity.getPackageName()).append(";\n\n");

    // Imports
    Set<String> imports = collectImports(entity);
    for (String importStr : imports) {
      sb.append("import ").append(importStr).append(";\n");
    }
    sb.append("\n");

    // Class annotations
    sb.append("@Entity\n");
    sb.append("@Table(name = \"").append(convertToSnakeCase(entity.getName())).append("\")\n");
    sb.append("@Data\n");
    sb.append("@NoArgsConstructor\n");
    sb.append("@AllArgsConstructor\n");
    sb.append("@Builder\n");

    // Custom annotations
    for (String annotation : entity.getAnnotations()) {
      sb.append("@").append(annotation).append("\n");
    }

    // Class declaration
    sb.append("public class ").append(entity.getName());
    if (entity.getExtendsFrom() != null) {
      sb.append(" extends ").append(entity.getExtendsFrom());
    }
    sb.append(" {\n\n");

    // Fields
    for (FieldModel field : entity.getFields()) {
      generateField(sb, field);
    }

    sb.append("}\n");

    return sb.toString();
  }

  private void generateField(StringBuilder sb, FieldModel field) {
    // Check if field is transient and add @Transient annotation
    if (field.isTransientField()) {
      sb.append("    @Transient\n");
    } else {
      // JPA annotations for relationships
      if (field.isRelationship()) {
        sb.append("    @").append(field.getRelationshipType()).append("\n");
        if ("OneToMany".equals(field.getRelationshipType()) || "ManyToMany".equals(field.getRelationshipType())) {
          sb.append("    @JoinColumn(name = \"").append(field.getName().toLowerCase()).append("_id\")\n");
        }
      } else {
        // Column annotation for simple fields
        sb.append("    @Column(name = \"").append(field.getName().toLowerCase()).append("\"");
        if (!field.isOptional()) {
          sb.append(", nullable = false");
        }
        sb.append(")\n");

        // Add @Id for id fields
        if ("id".equals(field.getName().toLowerCase())) {
          sb.append("    @Id\n");
          if ("UUID".equals(field.getJavaType())) {
            sb.append("    @GeneratedValue(generator = \"UUID\")\n");
            sb.append("    @GenericGenerator(name = \"UUID\", strategy = \"org.hibernate.id.UUIDGenerator\")\n");
          } else if ("Integer".equals(field.getJavaType()) || "Long".equals(field.getJavaType())) {
            sb.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
          }
        }
      }
    }

    // Bean Validation annotations
    for (ValidationModel validation : field.getValidations()) {
      generateValidationAnnotation(sb, validation);
    }

    // Custom annotations
    for (String annotation : field.getAnnotations()) {
      sb.append("    @").append(annotation).append("\n");
    }

    // Field declaration
    sb.append("    private ").append(field.getJavaType()).append(" ").append(field.getName()).append(";\n\n");
  }

  private void generateValidationAnnotation(StringBuilder sb, ValidationModel validation) {
    String type = validation.getType();
    if ("min".equals(type)) {
      Object value = validation.getParameter("value");
      sb.append("    @Min(").append(value).append(")\n");
    } else if ("max".equals(type)) {
      Object value = validation.getParameter("value");
      sb.append("    @Max(").append(value).append(")\n");
    } else if ("size".equals(type)) {
      Object min = validation.getParameter("min");
      Object max = validation.getParameter("max");
      sb.append("    @Size(min = ").append(min).append(", max = ").append(max).append(")\n");
    } else if ("pattern".equals(type)) {
      Object regexp = validation.getParameter("regexp");
      sb.append("    @Pattern(regexp = ").append(regexp).append(")\n");
    } else if ("notNull".equals(type)) {
      sb.append("    @NotNull\n");
    } else if ("notBlank".equals(type)) {
      sb.append("    @NotBlank\n");
    }
  }

  private Set<String> collectImports(EntityModel entity) {
    Set<String> imports = new HashSet<>();

    // JPA imports
    imports.add("jakarta.persistence.*");

    // Lombok imports
    imports.add("lombok.Data");
    imports.add("lombok.NoArgsConstructor");
    imports.add("lombok.AllArgsConstructor");
    imports.add("lombok.Builder");

    // Bean Validation imports
    imports.add("jakarta.validation.constraints.*");

    // Hibernate imports (for UUID generation)
    imports.add("org.hibernate.annotations.GenericGenerator");

    // Type-specific imports
    for (FieldModel field : entity.getFields()) {
      String javaType = field.getJavaType();
      if (TypeMapper.needsImport(javaType)) {
        imports.add(TypeMapper.getImport(javaType));
      }

      if (field.isRelationship()) {
        String collectionImport = TypeMapper.getCollectionImport(field.getRelationshipType());
        if (collectionImport != null) {
          imports.add(collectionImport);
        }
      }
    }

    return imports;
  }

}
