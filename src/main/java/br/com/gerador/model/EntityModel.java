package br.com.gerador.model;

import java.util.ArrayList;
import java.util.List;

public class EntityModel {
  private String name;
  private String packageName;
  private String extendsFrom;
  private List<FieldModel> fields;
  private List<String> annotations;

  public EntityModel(String name, String packageName) {
    this.name = name;
    this.packageName = packageName;
    this.fields = new ArrayList<>();
    this.annotations = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getExtendsFrom() {
    return extendsFrom;
  }

  public void setExtendsFrom(String extendsFrom) {
    this.extendsFrom = extendsFrom;
  }

  public List<FieldModel> getFields() {
    return fields;
  }

  public void setFields(List<FieldModel> fields) {
    this.fields = fields;
  }

  public void addField(FieldModel field) {
    this.fields.add(field);
  }

  public List<String> getAnnotations() {
    return annotations;
  }

  public void setAnnotations(List<String> annotations) {
    this.annotations = annotations;
  }

  public void addAnnotation(String annotation) {
    this.annotations.add(annotation);
  }
}
