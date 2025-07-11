package br.com.gerador.util;

import java.util.HashMap;
import java.util.Map;

public class TypeMapper {
  private static final Map<String, String> TYPE_MAPPING = new HashMap<>();
  private static final Map<String, String> IMPORT_MAPPING = new HashMap<>();

  static {
    // Tipos primitivos
    TYPE_MAPPING.put("string", "String");
    TYPE_MAPPING.put("uuid", "UUID");
    TYPE_MAPPING.put("integer", "Integer");
    TYPE_MAPPING.put("long", "Long");
    TYPE_MAPPING.put("double", "Double");
    TYPE_MAPPING.put("boolean", "Boolean");
    TYPE_MAPPING.put("date", "LocalDate");
    TYPE_MAPPING.put("datetime", "LocalDateTime");
    TYPE_MAPPING.put("decimal", "BigDecimal");

    // Imports necessários
    IMPORT_MAPPING.put("UUID", "java.util.UUID");
    IMPORT_MAPPING.put("LocalDate", "java.time.LocalDate");
    IMPORT_MAPPING.put("LocalDateTime", "java.time.LocalDateTime");
    IMPORT_MAPPING.put("BigDecimal", "java.math.BigDecimal");
    IMPORT_MAPPING.put("List", "java.util.List");
    IMPORT_MAPPING.put("Set", "java.util.Set");
  }

  public static String mapType(String dslType) {
    return TYPE_MAPPING.getOrDefault(dslType, dslType);
  }

  public static String getImport(String javaType) {
    return IMPORT_MAPPING.get(javaType);
  }

  public static boolean needsImport(String javaType) {
    return IMPORT_MAPPING.containsKey(javaType);
  }

  public static String mapRelationshipType(String relationshipType, String targetEntity) {
    if ("OneToMany".equals(relationshipType)) {
      return "List<" + targetEntity + ">";
    } else if ("ManyToMany".equals(relationshipType)) {
      return "Set<" + targetEntity + ">";
    } else if ("OneToOne".equals(relationshipType) || "ManyToOne".equals(relationshipType)) {
      return targetEntity;
    } else {
      throw new IllegalArgumentException("Tipo de relacionamento desconhecido: " + relationshipType);
    }
  }

  public static String getCollectionImport(String relationshipType) {
    if ("OneToMany".equals(relationshipType)) {
      return "java.util.List";
    } else if ("ManyToMany".equals(relationshipType)) {
      return "java.util.Set";
    } else {
      return null;
    }
  }
}
