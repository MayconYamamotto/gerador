package br.com.gerador.generator;

import br.com.gerador.model.EntityModel;
import br.com.gerador.model.FieldModel;
import br.com.gerador.model.ValidationModel;

import java.util.HashSet;
import java.util.Set;

public class DtoGenerator {

  public String generateCreateDto(EntityModel entity, String basePackage) {
    StringBuilder sb = new StringBuilder();

    // Package declaration
    sb.append("package ").append(basePackage).append(".application.dto;\n\n");

    // Imports
    Set<String> imports = collectImports(entity, true);
    for (String importStr : imports) {
      sb.append("import ").append(importStr).append(";\n");
    }
    sb.append("\n");

    // Class annotations
    sb.append("@Data\n");
    sb.append("@NoArgsConstructor\n");
    sb.append("@AllArgsConstructor\n");
    sb.append("@Builder\n");

    // Class declaration
    sb.append("public class Create").append(entity.getName()).append("Dto {\n\n");

    // Fields (excluding id and audit fields)
    for (FieldModel field : entity.getFields()) {
      if (!isIdField(field) && !isAuditField(field)) {
        generateDtoField(sb, field);
      }
    }

    sb.append("}\n");

    return sb.toString();
  }

  public String generateUpdateDto(EntityModel entity, String basePackage) {
    StringBuilder sb = new StringBuilder();

    // Package declaration
    sb.append("package ").append(basePackage).append(".application.dto;\n\n");

    // Imports
    Set<String> imports = collectImports(entity, true);
    for (String importStr : imports) {
      sb.append("import ").append(importStr).append(";\n");
    }
    sb.append("\n");

    // Class annotations
    sb.append("@Data\n");
    sb.append("@NoArgsConstructor\n");
    sb.append("@AllArgsConstructor\n");
    sb.append("@Builder\n");

    // Class declaration
    sb.append("public class Update").append(entity.getName()).append("Dto {\n\n");

    // Fields (excluding audit fields)
    for (FieldModel field : entity.getFields()) {
      if (!isAuditField(field)) {
        generateDtoField(sb, field);
      }
    }

    sb.append("}\n");

    return sb.toString();
  }

  public String generateResponseDto(EntityModel entity, String basePackage) {
    StringBuilder sb = new StringBuilder();

    // Package declaration
    sb.append("package ").append(basePackage).append(".application.dto;\n\n");

    // Imports
    Set<String> imports = collectImports(entity, false);
    for (String importStr : imports) {
      sb.append("import ").append(importStr).append(";\n");
    }
    sb.append("\n");

    // Class annotations
    sb.append("@Data\n");
    sb.append("@NoArgsConstructor\n");
    sb.append("@AllArgsConstructor\n");
    sb.append("@Builder\n");

    // Class declaration
    sb.append("public class ").append(entity.getName()).append("ResponseDto {\n\n");

    // All fields for response
    for (FieldModel field : entity.getFields()) {
      generateResponseDtoField(sb, field);
    }

    sb.append("}\n");

    return sb.toString();
  }

  private void generateDtoField(StringBuilder sb, FieldModel field) {
    // Bean Validation annotations
    for (ValidationModel validation : field.getValidations()) {
      generateValidationAnnotation(sb, validation);
    }

    // Add @NotNull for required fields
    if (!field.isOptional() && !isIdField(field)) {
      sb.append("    @NotNull(message = \"").append(field.getName()).append(" é obrigatório\")\n");
    }

    // Add @NotBlank for string fields
    if ("String".equals(field.getJavaType()) && !field.isOptional()) {
      sb.append("    @NotBlank(message = \"").append(field.getName()).append(" não pode estar vazio\")\n");
    }

    // Field declaration
    sb.append("    private ").append(field.getJavaType()).append(" ").append(field.getName()).append(";\n\n");
  }

  private void generateResponseDtoField(StringBuilder sb, FieldModel field) {
    // Field declaration (no validations needed for response DTOs)
    sb.append("    private ").append(field.getJavaType()).append(" ").append(field.getName()).append(";\n\n");
  }

  private void generateValidationAnnotation(StringBuilder sb, ValidationModel validation) {
    String type = validation.getType();
    if ("min".equals(type)) {
      Object value = validation.getParameter("value");
      sb.append("    @Min(value = ").append(value).append(", message = \"Valor mínimo é ").append(value)
          .append("\")\n");
    } else if ("max".equals(type)) {
      Object value = validation.getParameter("value");
      sb.append("    @Max(value = ").append(value).append(", message = \"Valor máximo é ").append(value)
          .append("\")\n");
    } else if ("notNull".equals(type)) {
      sb.append("    @NotNull(message = \"Campo obrigatório\")\n");
    } else if ("notBlank".equals(type)) {
      sb.append("    @NotBlank(message = \"Campo não pode estar vazio\")\n");
    }
  }

  private Set<String> collectImports(EntityModel entity, boolean includeValidations) {
    Set<String> imports = new HashSet<>();

    // Lombok imports
    imports.add("lombok.Data");
    imports.add("lombok.NoArgsConstructor");
    imports.add("lombok.AllArgsConstructor");
    imports.add("lombok.Builder");

    if (includeValidations) {
      // Bean Validation imports
      imports.add("jakarta.validation.constraints.*");
    }

    // Type-specific imports
    for (FieldModel field : entity.getFields()) {
      String javaType = field.getJavaType();
      if ("UUID".equals(javaType)) {
        imports.add("java.util.UUID");
      } else if ("LocalDate".equals(javaType)) {
        imports.add("java.time.LocalDate");
      } else if ("LocalDateTime".equals(javaType)) {
        imports.add("java.time.LocalDateTime");
      } else if ("BigDecimal".equals(javaType)) {
        imports.add("java.math.BigDecimal");
      }
    }

    return imports;
  }

  private boolean isIdField(FieldModel field) {
    return "id".equals(field.getName().toLowerCase());
  }

  private boolean isAuditField(FieldModel field) {
    String name = field.getName().toLowerCase();
    return name.equals("criadoem") || name.equals("atualizadoem") ||
        name.equals("createdat") || name.equals("updatedat");
  }
}
