package br.com.gerador.generator;

import br.com.gerador.model.EntityModel;

public class RepositoryGenerator {

  public String generateRepositoryInterface(EntityModel entity, String basePackage) {
    StringBuilder sb = new StringBuilder();

    // Package declaration
    sb.append("package ").append(basePackage).append(".domain.repository;\n\n");

    // Imports
    sb.append("import ").append(entity.getPackageName()).append(".").append(entity.getName()).append(";\n");
    sb.append("import org.springframework.data.jpa.repository.JpaRepository;\n");
    sb.append("import org.springframework.data.jpa.repository.Query;\n");
    sb.append("import org.springframework.data.repository.query.Param;\n");
    sb.append("import org.springframework.stereotype.Repository;\n");
    sb.append("import java.util.List;\n");
    sb.append("import java.util.Optional;\n");
    sb.append("import java.util.UUID;\n\n");

    // Interface declaration
    sb.append("@Repository\n");
    sb.append("public interface ").append(entity.getName()).append("Repository extends JpaRepository<")
        .append(entity.getName()).append(", UUID> {\n\n");

    // Custom finder methods
    sb.append("    @Query(\"SELECT e FROM ").append(entity.getName()).append(" e WHERE e.ativo = true\")\n");
    sb.append("    List<").append(entity.getName()).append("> findAllAtivos();\n\n");

    // Find by name if exists
    if (hasNameField(entity)) {
      sb.append("    @Query(\"SELECT e FROM ").append(entity.getName())
          .append(" e WHERE e.nome LIKE %:nome% AND e.ativo = true\")\n");
      sb.append("    List<").append(entity.getName())
          .append("> findByNomeContainingIgnoreCaseAndAtivoTrue(@Param(\"nome\") String nome);\n\n");
    }

    sb.append("    @Query(\"SELECT e FROM ").append(entity.getName())
        .append(" e WHERE e.id = :id AND e.ativo = true\")\n");
    sb.append("    Optional<").append(entity.getName()).append("> findByIdAndAtivoTrue(@Param(\"id\") UUID id);\n\n");

    sb.append("}\n");

    return sb.toString();
  }

  private boolean hasNameField(EntityModel entity) {
    return entity.getFields().stream()
        .anyMatch(field -> "nome".equals(field.getName()) || "name".equals(field.getName()));
  }
}
