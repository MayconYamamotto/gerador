package br.com.gerador.model;

public class CrudConfigModel {
  private boolean generateRepository = true;
  private boolean generateService = true;
  private boolean generateController = true;
  private boolean generateDto = true;
  private boolean dddLayers = true;

  public CrudConfigModel() {
  }

  public boolean isGenerateRepository() {
    return generateRepository;
  }

  public void setGenerateRepository(boolean generateRepository) {
    this.generateRepository = generateRepository;
  }

  public boolean isGenerateService() {
    return generateService;
  }

  public void setGenerateService(boolean generateService) {
    this.generateService = generateService;
  }

  public boolean isGenerateController() {
    return generateController;
  }

  public void setGenerateController(boolean generateController) {
    this.generateController = generateController;
  }

  public boolean isGenerateDto() {
    return generateDto;
  }

  public void setGenerateDto(boolean generateDto) {
    this.generateDto = generateDto;
  }

  public boolean isDddLayers() {
    return dddLayers;
  }

  public void setDddLayers(boolean dddLayers) {
    this.dddLayers = dddLayers;
  }
}
