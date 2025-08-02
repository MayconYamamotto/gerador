package br.com.gerador.visitor;

import br.com.gerador.generator.*;
import br.com.gerador.grammar.antlr4.ProjetoDSLBaseVisitor;
import br.com.gerador.grammar.antlr4.ProjetoDSLParser;
import br.com.gerador.model.CrudConfigModel;
import br.com.gerador.model.EntityModel;
import br.com.gerador.model.FieldModel;
import br.com.gerador.model.ValidationModel;
import br.com.gerador.util.TypeMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GeradorJavaVisitor extends ProjetoDSLBaseVisitor<Void> {
    private String basePackage;
    private final JavaEntityGenerator entityGenerator;
    private final RepositoryGenerator repositoryGenerator;
    private final DtoGenerator dtoGenerator;
    private final ServiceGenerator serviceGenerator;
    private final ControllerGenerator controllerGenerator;
    private final FlywayMigrationGenerator migrationGenerator;
    private final List<EntityModel> entities;
    private CrudConfigModel crudConfig;

    public GeradorJavaVisitor() {
        this.entityGenerator = new JavaEntityGenerator();
        this.repositoryGenerator = new RepositoryGenerator();
        this.dtoGenerator = new DtoGenerator();
        this.serviceGenerator = new ServiceGenerator();
        this.controllerGenerator = new ControllerGenerator();
        this.migrationGenerator = new FlywayMigrationGenerator();
        this.entities = new ArrayList<>();
        this.crudConfig = new CrudConfigModel(); // Default configuration
    }

    @Override
    public Void visitPackageDecl(ProjetoDSLParser.PackageDeclContext ctx) {
        // Extrair o nome do pacote
        StringBuilder packageNameBuilder = new StringBuilder();
        ProjetoDSLParser.PackageNameContext packageNameCtx = ctx.packageName();

        for (int i = 0; i < packageNameCtx.ID().size(); i++) {
            if (i > 0) {
                packageNameBuilder.append(".");
            }
            packageNameBuilder.append(packageNameCtx.ID(i).getText());
        }

        this.basePackage = packageNameBuilder.toString();
        System.out.println("\nProcessando pacote: " + this.basePackage);

        // Process CRUD configurations first
        if (ctx.crudConfig() != null) {
            for (ProjetoDSLParser.CrudConfigContext crudCtx : ctx.crudConfig()) {
                visitCrudConfig(crudCtx);
            }
        }

        // Visit all entities in this package
        for (ProjetoDSLParser.EntityDeclContext entityCtx : ctx.entityDecl()) {
            visitEntityDecl(entityCtx);
        }

        return null;
    }

    public Void visitCrudConfig(ProjetoDSLParser.CrudConfigContext ctx) {
        System.out.println("Processando configuração de CRUD");

        if (ctx.crudOptions() != null) {
            for (ProjetoDSLParser.CrudOptionsContext option : ctx.crudOptions()) {
                String optionText = option.getText();

                if (optionText.contains("generateRepository")) {
                    boolean value = optionText.contains("true");
                    this.crudConfig.setGenerateRepository(value);
                    System.out.println("generateRepository: " + value);
                } else if (optionText.contains("generateService")) {
                    boolean value = optionText.contains("true");
                    this.crudConfig.setGenerateService(value);
                    System.out.println("generateService: " + value);
                } else if (optionText.contains("generateController")) {
                    boolean value = optionText.contains("true");
                    this.crudConfig.setGenerateController(value);
                    System.out.println("generateController: " + value);
                } else if (optionText.contains("generateDto")) {
                    boolean value = optionText.contains("true");
                    this.crudConfig.setGenerateDto(value);
                    System.out.println("generateDto: " + value);
                } else if (optionText.contains("dddLayers")) {
                    boolean value = optionText.contains("true");
                    this.crudConfig.setDddLayers(value);
                    System.out.println("dddLayers: " + value);
                }
            }
        }

        return null;
    }

    @Override
    public Void visitEntityDecl(ProjetoDSLParser.EntityDeclContext ctx) {
        String entityName = ctx.ID().getText();
        System.out.println("Processando entidade: " + entityName);

        // Usar o package base + .entity para as entidades
        String entityPackage = this.basePackage + ".domain.entity";
        EntityModel entity = new EntityModel(entityName, entityPackage);

        // Process fields
        if (ctx.fieldDecl() != null) {
            for (ProjetoDSLParser.FieldDeclContext fieldCtx : ctx.fieldDecl()) {
                FieldModel field = processField(fieldCtx);
                entity.addField(field);
            }
        }

        entities.add(entity);

        // Generate all CRUD components based on configuration
        generateCrudComponents(entity);

        return null;
    }

    private void generateCrudComponents(EntityModel entity) {
        // Always generate the entity
        generateEntityFile(entity);

        // Generate Flyway migration
        generateFlywayMigration(entity);

        if (crudConfig.isDddLayers()) {
            System.out.println("Gerando componentes DDD para " + entity.getName());

            // Generate Repository Interface (Domain Layer)
            if (crudConfig.isGenerateRepository()) {
                generateRepositoryInterface(entity);
            }

            // Generate DTOs (Application Layer)
            if (crudConfig.isGenerateDto()) {
                generateDtos(entity);
            }

            // Generate Service (Application Layer)
            if (crudConfig.isGenerateService()) {
                generateService(entity);
            }

            // Generate Controller (Interface Layer)
            if (crudConfig.isGenerateController()) {
                generateController(entity);
            }
        }
    }

    private FieldModel processField(ProjetoDSLParser.FieldDeclContext fieldCtx) {
        String fieldName = fieldCtx.ID().getText();
        String fieldType = fieldCtx.type().getText();

        FieldModel field = new FieldModel(fieldName, fieldType);

        // Check if it's optional
        field.setOptional(fieldCtx.option() != null);

        // Check if it's transient
        field.setTransientField(fieldCtx.modifier() != null && fieldCtx.modifier().getText().equals("transient"));

        // Map the type
        String javaType = TypeMapper.mapType(fieldType);
        field.setJavaType(javaType);
        field.setRelationship(false);

        // Process validation (simplified for now)
        if (fieldCtx.validation() != null) {
            ValidationModel validation = processValidation(fieldCtx.validation());
            if (validation != null) {
                field.addValidation(validation);
            }
        }

        return field;
    }

    private ValidationModel processValidation(ProjetoDSLParser.ValidationContext validationCtx) {
        String validationText = validationCtx.getText();

        if (validationText.startsWith("min(")) {
            ValidationModel validation = new ValidationModel("min");
            // Extract the number from min(number, "message")
            String content = validationText.substring(4, validationText.length() - 1);
            String[] parts = content.split(",");
            if (parts.length > 0) {
                String value = parts[0].trim();
                validation.addParameter("value", value);
            }
            return validation;
        } else if (validationText.startsWith("max(")) {
            ValidationModel validation = new ValidationModel("max");
            String content = validationText.substring(4, validationText.length() - 1);
            validation.addParameter("value", content.trim());
            return validation;
        } else if (validationText.equals("notNull")) {
            return new ValidationModel("notNull");
        } else if (validationText.equals("notBlank")) {
            return new ValidationModel("notBlank");
        }

        return null;
    }

    private void generateEntityFile(EntityModel entity) {
        try {
            String generatedCode = entityGenerator.generateEntity(entity);

            // Converter o package em caminho de diretórios
            String packagePath = entity.getPackageName().replace(".", "/");
            Path path = Paths.get("target/generated-sources/" + packagePath + "/" + entity.getName() + ".java");

            // Criar diretórios se não existirem
            Files.createDirectories(path.getParent());
            Files.write(path, generatedCode.getBytes(StandardCharsets.UTF_8));

            System.out.println("Arquivo " + entity.getName() + ".java gerado com sucesso em: " + path);
        } catch (IOException e) {
            System.err.println("Erro ao gerar arquivo " + entity.getName() + ".java: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void generateRepositoryInterface(EntityModel entity) {
        try {
            String generatedCode = repositoryGenerator.generateRepositoryInterface(entity, this.basePackage);

            String packagePath = (this.basePackage + ".domain.repository").replace(".", "/");
            Path path = Paths
                    .get("target/generated-sources/" + packagePath + "/" + entity.getName() + "Repository.java");

            Files.createDirectories(path.getParent());
            Files.write(path, generatedCode.getBytes(StandardCharsets.UTF_8));

            System.out.println("Repository " + entity.getName() + "Repository.java gerado com sucesso em: " + path);
        } catch (IOException e) {
            System.err.println("Erro ao gerar repository " + entity.getName() + "Repository.java: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void generateDtos(EntityModel entity) {
        try {
            // Generate Create DTO
            String createDtoCode = dtoGenerator.generateCreateDto(entity, this.basePackage);
            String packagePath = (this.basePackage + ".application.dto").replace(".", "/");
            Path createDtoPath = Paths
                    .get("target/generated-sources/" + packagePath + "/Create" + entity.getName() + "Dto.java");

            Files.createDirectories(createDtoPath.getParent());
            Files.write(createDtoPath, createDtoCode.getBytes(StandardCharsets.UTF_8));
            System.out.println("Create" + entity.getName() + "Dto.java gerado com sucesso");

            // Generate Update DTO
            String updateDtoCode = dtoGenerator.generateUpdateDto(entity, this.basePackage);
            Path updateDtoPath = Paths
                    .get("target/generated-sources/" + packagePath + "/Update" + entity.getName() + "Dto.java");

            Files.write(updateDtoPath, updateDtoCode.getBytes(StandardCharsets.UTF_8));
            System.out.println("Update" + entity.getName() + "Dto.java gerado com sucesso");

            // Generate Response DTO
            String responseDtoCode = dtoGenerator.generateResponseDto(entity, this.basePackage);
            Path responseDtoPath = Paths
                    .get("target/generated-sources/" + packagePath + "/" + entity.getName() + "ResponseDto.java");

            Files.write(responseDtoPath, responseDtoCode.getBytes(StandardCharsets.UTF_8));
            System.out.println(entity.getName() + "ResponseDto.java gerado com sucesso");

        } catch (IOException e) {
            System.err.println("Erro ao gerar DTOs para " + entity.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void generateService(EntityModel entity) {
        try {
            String generatedCode = serviceGenerator.generateService(entity, this.basePackage);

            String packagePath = (this.basePackage + ".application.service").replace(".", "/");
            Path path = Paths.get("target/generated-sources/" + packagePath + "/" + entity.getName() + "Service.java");

            Files.createDirectories(path.getParent());
            Files.write(path, generatedCode.getBytes(StandardCharsets.UTF_8));

            System.out.println("Service " + entity.getName() + "Service.java gerado com sucesso em: " + path);
        } catch (IOException e) {
            System.err.println("Erro ao gerar service " + entity.getName() + "Service.java: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void generateController(EntityModel entity) {
        try {
            String generatedCode = controllerGenerator.generateController(entity, this.basePackage);

            String packagePath = (this.basePackage + ".interfaces.controller").replace(".", "/");
            Path path = Paths
                    .get("target/generated-sources/" + packagePath + "/" + entity.getName() + "Controller.java");

            Files.createDirectories(path.getParent());
            Files.write(path, generatedCode.getBytes(StandardCharsets.UTF_8));

            System.out.println("Controller " + entity.getName() + "Controller.java gerado com sucesso em: " + path);
        } catch (IOException e) {
            System.err.println("Erro ao gerar controller " + entity.getName() + "Controller.java: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void generateFlywayMigration(EntityModel entity) {
        try {
            System.out.println("Gerando migração Flyway para " + entity.getName());
            migrationGenerator.generateMigration(entity, "target/generated-sources");
        } catch (Exception e) {
            System.err.println("Erro ao gerar migração Flyway para " + entity.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
