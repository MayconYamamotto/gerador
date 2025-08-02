package br.com.gerador.generator;

import br.com.gerador.model.EntityModel;

public class ServiceGenerator {

  public String generateService(EntityModel entity, String basePackage) {
    StringBuilder sb = new StringBuilder();

    // Package declaration
    sb.append("package ").append(basePackage).append(".application.service;\n\n");

    // Imports
    sb.append("import ").append(basePackage).append(".application.dto.Create").append(entity.getName())
        .append("Dto;\n");
    sb.append("import ").append(basePackage).append(".application.dto.Update").append(entity.getName())
        .append("Dto;\n");
    sb.append("import ").append(basePackage).append(".application.dto.").append(entity.getName())
        .append("ResponseDto;\n");
    sb.append("import ").append(basePackage).append(".domain.repository.").append(entity.getName())
        .append("Repository;\n");
    sb.append("import ").append(entity.getPackageName()).append(".").append(entity.getName()).append(";\n");
    sb.append("import org.springframework.beans.BeanUtils;\n");
    sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
    sb.append("import org.springframework.data.domain.Page;\n");
    sb.append("import org.springframework.data.domain.Pageable;\n");
    sb.append("import org.springframework.stereotype.Service;\n");
    sb.append("import org.springframework.transaction.annotation.Transactional;\n");
    sb.append("import java.time.LocalDateTime;\n");
    sb.append("import java.util.List;\n");
    sb.append("import java.util.UUID;\n");
    sb.append("import java.util.stream.Collectors;\n\n");

    // Service annotation
    sb.append("@Service\n");
    sb.append("@Transactional\n");
    sb.append("public class ").append(entity.getName()).append("Service {\n\n");

    // Repository field
    sb.append("    @Autowired\n");
    sb.append("    private ").append(entity.getName()).append("Repository ").append(entity.getName().toLowerCase())
        .append("Repository;\n\n");

    // Create method
    generateCreateMethod(sb, entity);

    // Update method
    generateUpdateMethod(sb, entity);

    // Find by ID method
    generateFindByIdMethod(sb, entity);

    // Find all method
    generateFindAllMethod(sb, entity);

    // Find all paginated method
    generateFindAllPaginatedMethod(sb, entity);

    // Delete method (soft delete)
    generateDeleteMethod(sb, entity);

    // Activate method
    generateActivateMethod(sb, entity);

    // Search method (if has name field)
    if (hasNameField(entity)) {
      generateSearchMethod(sb, entity);
    }

    // Converter methods
    generateConverterMethods(sb, entity);

    sb.append("}\n");

    return sb.toString();
  }

  private void generateCreateMethod(StringBuilder sb, EntityModel entity) {
    sb.append("    public ").append(entity.getName()).append("ResponseDto create(Create").append(entity.getName())
        .append("Dto dto) {\n");
    sb.append("        ").append(entity.getName()).append(" entity = convertToEntity(dto);\n");
    sb.append("        entity.setId(UUID.randomUUID());\n");
    if (hasAuditField(entity, "criadoEm")) {
      sb.append("        entity.setCriadoEm(LocalDateTime.now());\n");
    }
    if (hasAtivoField(entity)) {
      sb.append("        entity.setAtivo(true);\n");
    }
    sb.append("        entity = ").append(entity.getName().toLowerCase()).append("Repository.save(entity);\n");
    sb.append("        return convertToResponseDto(entity);\n");
    sb.append("    }\n\n");
  }

  private void generateUpdateMethod(StringBuilder sb, EntityModel entity) {
    sb.append("    public ").append(entity.getName()).append("ResponseDto update(UUID id, Update")
        .append(entity.getName()).append("Dto dto) {\n");
    sb.append("        ").append(entity.getName()).append(" entity = ").append(entity.getName().toLowerCase())
        .append("Repository.findByIdAndAtivoTrue(id)\n");
    sb.append("                .orElseThrow(() -> new RuntimeException(\"").append(entity.getName())
        .append(" não encontrado com ID: \" + id));\n\n");
    sb.append("        BeanUtils.copyProperties(dto, entity, \"id\", \"criadoEm\", \"ativo\");\n");
    if (hasAuditField(entity, "atualizadoEm")) {
      sb.append("        entity.setAtualizadoEm(LocalDateTime.now());\n");
    }
    sb.append("        entity = ").append(entity.getName().toLowerCase()).append("Repository.save(entity);\n");
    sb.append("        return convertToResponseDto(entity);\n");
    sb.append("    }\n\n");
  }

  private void generateFindByIdMethod(StringBuilder sb, EntityModel entity) {
    sb.append("    @Transactional(readOnly = true)\n");
    sb.append("    public ").append(entity.getName()).append("ResponseDto findById(UUID id) {\n");
    sb.append("        ").append(entity.getName()).append(" entity = ").append(entity.getName().toLowerCase())
        .append("Repository.findByIdAndAtivoTrue(id)\n");
    sb.append("                .orElseThrow(() -> new RuntimeException(\"").append(entity.getName())
        .append(" não encontrado com ID: \" + id));\n");
    sb.append("        return convertToResponseDto(entity);\n");
    sb.append("    }\n\n");
  }

  private void generateFindAllMethod(StringBuilder sb, EntityModel entity) {
    sb.append("    @Transactional(readOnly = true)\n");
    sb.append("    public List<").append(entity.getName()).append("ResponseDto> findAll() {\n");
    sb.append("        return ").append(entity.getName().toLowerCase()).append("Repository.findAllAtivos().stream()\n");
    sb.append("                .map(this::convertToResponseDto)\n");
    sb.append("                .collect(Collectors.toList());\n");
    sb.append("    }\n\n");
  }

  private void generateFindAllPaginatedMethod(StringBuilder sb, EntityModel entity) {
    sb.append("    @Transactional(readOnly = true)\n");
    sb.append("    public Page<").append(entity.getName()).append("ResponseDto> findAll(Pageable pageable) {\n");
    sb.append("        return ").append(entity.getName().toLowerCase()).append("Repository.findAll(pageable)\n");
    sb.append("                .map(this::convertToResponseDto);\n");
    sb.append("    }\n\n");
  }

  private void generateDeleteMethod(StringBuilder sb, EntityModel entity) {
    sb.append("    public void delete(UUID id) {\n");
    sb.append("        ").append(entity.getName()).append(" entity = ").append(entity.getName().toLowerCase())
        .append("Repository.findByIdAndAtivoTrue(id)\n");
    sb.append("                .orElseThrow(() -> new RuntimeException(\"").append(entity.getName())
        .append(" não encontrado com ID: \" + id));\n");
    if (hasAtivoField(entity)) {
      sb.append("        entity.setAtivo(false);\n");
      if (hasAuditField(entity, "atualizadoEm")) {
        sb.append("        entity.setAtualizadoEm(LocalDateTime.now());\n");
      }
      sb.append("        ").append(entity.getName().toLowerCase()).append("Repository.save(entity);\n");
    } else {
      sb.append("        ").append(entity.getName().toLowerCase()).append("Repository.delete(entity);\n");
    }
    sb.append("    }\n\n");
  }

  private void generateActivateMethod(StringBuilder sb, EntityModel entity) {
    if (hasAtivoField(entity)) {
      sb.append("    public ").append(entity.getName()).append("ResponseDto activate(UUID id) {\n");
      sb.append("        ").append(entity.getName()).append(" entity = ").append(entity.getName().toLowerCase())
          .append("Repository.findById(id)\n");
      sb.append("                .orElseThrow(() -> new RuntimeException(\"").append(entity.getName())
          .append(" não encontrado com ID: \" + id));\n");
      sb.append("        entity.setAtivo(true);\n");
      if (hasAuditField(entity, "atualizadoEm")) {
        sb.append("        entity.setAtualizadoEm(LocalDateTime.now());\n");
      }
      sb.append("        entity = ").append(entity.getName().toLowerCase()).append("Repository.save(entity);\n");
      sb.append("        return convertToResponseDto(entity);\n");
      sb.append("    }\n\n");
    }
  }

  private void generateSearchMethod(StringBuilder sb, EntityModel entity) {
    sb.append("    @Transactional(readOnly = true)\n");
    sb.append("    public List<").append(entity.getName()).append("ResponseDto> searchByNome(String nome) {\n");
    sb.append("        return ").append(entity.getName().toLowerCase())
        .append("Repository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome).stream()\n");
    sb.append("                .map(this::convertToResponseDto)\n");
    sb.append("                .collect(Collectors.toList());\n");
    sb.append("    }\n\n");
  }

  private void generateConverterMethods(StringBuilder sb, EntityModel entity) {
    // Convert DTO to Entity
    sb.append("    private ").append(entity.getName()).append(" convertToEntity(Create").append(entity.getName())
        .append("Dto dto) {\n");
    sb.append("        ").append(entity.getName()).append(" entity = new ").append(entity.getName()).append("();\n");
    sb.append("        BeanUtils.copyProperties(dto, entity);\n");
    sb.append("        return entity;\n");
    sb.append("    }\n\n");

    // Convert Entity to Response DTO
    sb.append("    private ").append(entity.getName()).append("ResponseDto convertToResponseDto(")
        .append(entity.getName()).append(" entity) {\n");
    sb.append("        ").append(entity.getName()).append("ResponseDto dto = new ").append(entity.getName())
        .append("ResponseDto();\n");
    sb.append("        BeanUtils.copyProperties(entity, dto);\n");
    sb.append("        return dto;\n");
    sb.append("    }\n\n");
  }

  private boolean hasNameField(EntityModel entity) {
    return entity.getFields().stream()
        .anyMatch(field -> "nome".equals(field.getName()) || "name".equals(field.getName()));
  }

  private boolean hasAtivoField(EntityModel entity) {
    return entity.getFields().stream()
        .anyMatch(field -> "ativo".equals(field.getName()) || "active".equals(field.getName()));
  }

  private boolean hasAuditField(EntityModel entity, String fieldName) {
    return entity.getFields().stream()
        .anyMatch(field -> fieldName.equals(field.getName()));
  }
}
