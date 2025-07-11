package br.com.gerador.model;

import java.util.ArrayList;
import java.util.List;

public class FieldModel {
  private String name;
  private String type;
  private String javaType;
  private boolean optional;
  private boolean isRelationship;
  private String relationshipType;
  private String targetEntity;
  private List<ValidationModel> validations;
  private List<String> annotations;

  public FieldModel(String name, String type) {
    this.name = name;
    this.type = type;
    this.validations = new ArrayList<>();
    this.annotations = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getJavaType() {
    return javaType;
  }

  public void setJavaType(String javaType) {
    this.javaType = javaType;
  }

  public boolean isOptional() {
    return optional;
  }

  public void setOptional(boolean optional) {
    this.optional = optional;
  }

  public boolean isRelationship() {
    return isRelationship;
  }

  public void setRelationship(boolean relationship) {
    isRelationship = relationship;
  }

  public String getRelationshipType() {
    return relationshipType;
  }

  public void setRelationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
  }

  public String getTargetEntity() {
    return targetEntity;
  }

  public void setTargetEntity(String targetEntity) {
    this.targetEntity = targetEntity;
  }

  public List<ValidationModel> getValidations() {
    return validations;
  }

  public void setValidations(List<ValidationModel> validations) {
    this.validations = validations;
  }

  public void addValidation(ValidationModel validation) {
    this.validations.add(validation);
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
