package br.com.gerador.model;

import java.util.HashMap;
import java.util.Map;

public class ValidationModel {
  private String type;
  private Map<String, Object> parameters;

  public ValidationModel(String type) {
    this.type = type;
    this.parameters = new HashMap<>();
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<String, Object> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, Object> parameters) {
    this.parameters = parameters;
  }

  public void addParameter(String key, Object value) {
    this.parameters.put(key, value);
  }

  public Object getParameter(String key) {
    return this.parameters.get(key);
  }
}
