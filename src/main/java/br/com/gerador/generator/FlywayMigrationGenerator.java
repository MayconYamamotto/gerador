package br.com.gerador.generator;

import br.com.gerador.model.EntityModel;
import br.com.gerador.model.FieldModel;
import br.com.gerador.util.TypeMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlywayMigrationGenerator {

  private static final String MIGRATION_TEMPLATE = """
      -- Migration for table %s
      -- Generated at %s

      CREATE TABLE %s (
      %s
      );
      """;

  public void generateMigration(EntityModel entity, String outputPath) {
    try {
      String tableName = convertToSnakeCase(entity.getName());
      String migrationContent = generateMigrationContent(entity, tableName);
      String fileName = generateMigrationFileName(tableName);

      Path migrationDir = Paths.get(outputPath, "src", "main", "resources", "db", "migration");
      Files.createDirectories(migrationDir);

      Path migrationFile = migrationDir.resolve(fileName);
      Files.write(migrationFile, migrationContent.getBytes(StandardCharsets.UTF_8));

      System.out.println("✅ Migração Flyway gerada: " + migrationFile.toString());

    } catch (IOException e) {
      System.err.println("❌ Erro ao gerar migração Flyway: " + e.getMessage());
    }
  }

  private String generateMigrationContent(EntityModel entity, String tableName) {
    List<String> columns = new ArrayList<>();

    // Processar campos não transient
    for (FieldModel field : entity.getFields()) {
      if (!field.isTransientField()) { // Ignorar campos transient
        String columnDefinition = generateColumnDefinition(field);
        columns.add("    " + columnDefinition);
      }
    }

    String columnsString = columns.stream().collect(Collectors.joining(",\n"));

    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    return String.format(MIGRATION_TEMPLATE,
        entity.getName(),
        timestamp,
        tableName,
        columnsString);
  }

  private String generateColumnDefinition(FieldModel field) {
    String columnName = convertToSnakeCase(field.getName());
    String sqlType = TypeMapper.mapSqlType(field.getType());

    StringBuilder definition = new StringBuilder();
    definition.append(columnName).append(" ").append(sqlType);

    // Adicionar constraints baseadas no tipo e validações
    if ("id".equals(field.getName().toLowerCase())) {
      definition.append(" PRIMARY KEY");
    }

    if (!field.isOptional() && !"id".equals(field.getName().toLowerCase())) {
      definition.append(" NOT NULL");
    }

    // Adicionar validações específicas
    if (field.getValidations() != null) {
      for (var validation : field.getValidations()) {
        if ("notNull".equals(validation.getType()) || "notBlank".equals(validation.getType())) {
          if (!definition.toString().contains("NOT NULL")) {
            definition.append(" NOT NULL");
          }
        }
      }
    }

    return definition.toString();
  }

  private String generateMigrationFileName(String tableName) {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    return String.format("V%s__Create_%s_table.sql", timestamp, tableName);
  }

  private String convertToSnakeCase(String camelCase) {
    return camelCase.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
  }
}
