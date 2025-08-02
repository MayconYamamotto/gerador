package br.com.gerador.generator;

import br.com.gerador.model.EntityModel;

public class ControllerGenerator {

  public String generateController(EntityModel entity, String basePackage) {
    StringBuilder sb = new StringBuilder();

    // Package declaration
    sb.append("package ").append(basePackage).append(".interfaces.controller;\n\n");

    // Imports
    sb.append("import ").append(basePackage).append(".application.dto.Create").append(entity.getName())
        .append("Dto;\n");
    sb.append("import ").append(basePackage).append(".application.dto.Update").append(entity.getName())
        .append("Dto;\n");
    sb.append("import ").append(basePackage).append(".application.dto.").append(entity.getName())
        .append("ResponseDto;\n");
    sb.append("import ").append(basePackage).append(".application.service.").append(entity.getName())
        .append("Service;\n");
    sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
    sb.append("import org.springframework.data.domain.Page;\n");
    sb.append("import org.springframework.data.domain.Pageable;\n");
    sb.append("import org.springframework.http.HttpStatus;\n");
    sb.append("import org.springframework.http.ResponseEntity;\n");
    sb.append("import org.springframework.validation.annotation.Validated;\n");
    sb.append("import org.springframework.web.bind.annotation.*;\n");
    sb.append("import jakarta.validation.Valid;\n");
    sb.append("import jakarta.validation.constraints.NotNull;\n");
    sb.append("import io.swagger.v3.oas.annotations.Operation;\n");
    sb.append("import io.swagger.v3.oas.annotations.Parameter;\n");
    sb.append("import io.swagger.v3.oas.annotations.tags.Tag;\n");
    sb.append("import java.util.List;\n");
    sb.append("import java.util.UUID;\n\n");

    // Controller annotations
    sb.append("@RestController\n");
    sb.append("@RequestMapping(\"/api/").append(entity.getName().toLowerCase()).append("s\")\n");
    sb.append("@Validated\n");
    sb.append("@Tag(name = \"").append(entity.getName()).append("\", description = \"APIs para gerenciamento de ")
        .append(entity.getName()).append("\")\n");
    sb.append("public class ").append(entity.getName()).append("Controller {\n\n");

    // Service field
    sb.append("    @Autowired\n");
    sb.append("    private ").append(entity.getName()).append("Service ").append(entity.getName().toLowerCase())
        .append("Service;\n\n");

    // Create endpoint
    generateCreateEndpoint(sb, entity);

    // Update endpoint
    generateUpdateEndpoint(sb, entity);

    // Find by ID endpoint
    generateFindByIdEndpoint(sb, entity);

    // Find all endpoint
    generateFindAllEndpoint(sb, entity);

    // Find all paginated endpoint
    generateFindAllPaginatedEndpoint(sb, entity);

    // Delete endpoint
    generateDeleteEndpoint(sb, entity);

    // Activate endpoint (if has ativo field)
    if (hasAtivoField(entity)) {
      generateActivateEndpoint(sb, entity);
    }

    // Search endpoint (if has name field)
    if (hasNameField(entity)) {
      generateSearchEndpoint(sb, entity);
    }

    sb.append("}\n");

    return sb.toString();
  }

  private void generateCreateEndpoint(StringBuilder sb, EntityModel entity) {
    sb.append("    @PostMapping\n");
    sb.append("    @Operation(summary = \"Criar novo ").append(entity.getName()).append("\")\n");
    sb.append("    public ResponseEntity<").append(entity.getName()).append("ResponseDto> create(\n");
    sb.append("            @Valid @RequestBody Create").append(entity.getName()).append("Dto dto) {\n");
    sb.append("        try {\n");
    sb.append("            ").append(entity.getName()).append("ResponseDto response = ")
        .append(entity.getName().toLowerCase()).append("Service.create(dto);\n");
    sb.append("            return ResponseEntity.status(HttpStatus.CREATED).body(response);\n");
    sb.append("        } catch (Exception e) {\n");
    sb.append("            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();\n");
    sb.append("        }\n");
    sb.append("    }\n\n");
  }

  private void generateUpdateEndpoint(StringBuilder sb, EntityModel entity) {
    sb.append("    @PutMapping(\"/{id}\")\n");
    sb.append("    @Operation(summary = \"Atualizar ").append(entity.getName()).append(" por ID\")\n");
    sb.append("    public ResponseEntity<").append(entity.getName()).append("ResponseDto> update(\n");
    sb.append("            @Parameter(description = \"ID do ").append(entity.getName())
        .append("\") @PathVariable @NotNull UUID id,\n");
    sb.append("            @Valid @RequestBody Update").append(entity.getName()).append("Dto dto) {\n");
    sb.append("        try {\n");
    sb.append("            ").append(entity.getName()).append("ResponseDto response = ")
        .append(entity.getName().toLowerCase()).append("Service.update(id, dto);\n");
    sb.append("            return ResponseEntity.ok(response);\n");
    sb.append("        } catch (RuntimeException e) {\n");
    sb.append("            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n");
    sb.append("        } catch (Exception e) {\n");
    sb.append("            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();\n");
    sb.append("        }\n");
    sb.append("    }\n\n");
  }

  private void generateFindByIdEndpoint(StringBuilder sb, EntityModel entity) {
    sb.append("    @GetMapping(\"/{id}\")\n");
    sb.append("    @Operation(summary = \"Buscar ").append(entity.getName()).append(" por ID\")\n");
    sb.append("    public ResponseEntity<").append(entity.getName()).append("ResponseDto> findById(\n");
    sb.append("            @Parameter(description = \"ID do ").append(entity.getName())
        .append("\") @PathVariable @NotNull UUID id) {\n");
    sb.append("        try {\n");
    sb.append("            ").append(entity.getName()).append("ResponseDto response = ")
        .append(entity.getName().toLowerCase()).append("Service.findById(id);\n");
    sb.append("            return ResponseEntity.ok(response);\n");
    sb.append("        } catch (RuntimeException e) {\n");
    sb.append("            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n");
    sb.append("        }\n");
    sb.append("    }\n\n");
  }

  private void generateFindAllEndpoint(StringBuilder sb, EntityModel entity) {
    sb.append("    @GetMapping\n");
    sb.append("    @Operation(summary = \"Listar todos os ").append(entity.getName()).append("s ativos\")\n");
    sb.append("    public ResponseEntity<List<").append(entity.getName()).append("ResponseDto>> findAll() {\n");
    sb.append("        List<").append(entity.getName()).append("ResponseDto> response = ")
        .append(entity.getName().toLowerCase()).append("Service.findAll();\n");
    sb.append("        return ResponseEntity.ok(response);\n");
    sb.append("    }\n\n");
  }

  private void generateFindAllPaginatedEndpoint(StringBuilder sb, EntityModel entity) {
    sb.append("    @GetMapping(\"/paginated\")\n");
    sb.append("    @Operation(summary = \"Listar ").append(entity.getName()).append("s com paginação\")\n");
    sb.append("    public ResponseEntity<Page<").append(entity.getName()).append("ResponseDto>> findAllPaginated(\n");
    sb.append("            @Parameter(description = \"Configurações de paginação\") Pageable pageable) {\n");
    sb.append("        Page<").append(entity.getName()).append("ResponseDto> response = ")
        .append(entity.getName().toLowerCase()).append("Service.findAll(pageable);\n");
    sb.append("        return ResponseEntity.ok(response);\n");
    sb.append("    }\n\n");
  }

  private void generateDeleteEndpoint(StringBuilder sb, EntityModel entity) {
    sb.append("    @DeleteMapping(\"/{id}\")\n");
    sb.append("    @Operation(summary = \"Deletar ").append(entity.getName()).append(" por ID\")\n");
    sb.append("    public ResponseEntity<Void> delete(\n");
    sb.append("            @Parameter(description = \"ID do ").append(entity.getName())
        .append("\") @PathVariable @NotNull UUID id) {\n");
    sb.append("        try {\n");
    sb.append("            ").append(entity.getName().toLowerCase()).append("Service.delete(id);\n");
    sb.append("            return ResponseEntity.noContent().build();\n");
    sb.append("        } catch (RuntimeException e) {\n");
    sb.append("            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n");
    sb.append("        }\n");
    sb.append("    }\n\n");
  }

  private void generateActivateEndpoint(StringBuilder sb, EntityModel entity) {
    sb.append("    @PatchMapping(\"/{id}/activate\")\n");
    sb.append("    @Operation(summary = \"Ativar ").append(entity.getName()).append(" por ID\")\n");
    sb.append("    public ResponseEntity<").append(entity.getName()).append("ResponseDto> activate(\n");
    sb.append("            @Parameter(description = \"ID do ").append(entity.getName())
        .append("\") @PathVariable @NotNull UUID id) {\n");
    sb.append("        try {\n");
    sb.append("            ").append(entity.getName()).append("ResponseDto response = ")
        .append(entity.getName().toLowerCase()).append("Service.activate(id);\n");
    sb.append("            return ResponseEntity.ok(response);\n");
    sb.append("        } catch (RuntimeException e) {\n");
    sb.append("            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n");
    sb.append("        }\n");
    sb.append("    }\n\n");
  }

  private void generateSearchEndpoint(StringBuilder sb, EntityModel entity) {
    sb.append("    @GetMapping(\"/search\")\n");
    sb.append("    @Operation(summary = \"Buscar ").append(entity.getName()).append("s por nome\")\n");
    sb.append("    public ResponseEntity<List<").append(entity.getName()).append("ResponseDto>> searchByNome(\n");
    sb.append("            @Parameter(description = \"Nome para busca\") @RequestParam String nome) {\n");
    sb.append("        List<").append(entity.getName()).append("ResponseDto> response = ")
        .append(entity.getName().toLowerCase()).append("Service.searchByNome(nome);\n");
    sb.append("        return ResponseEntity.ok(response);\n");
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
}
