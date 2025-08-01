package br.com.gerador.visitor;

import br.com.gerador.generator.JavaEntityGenerator;
import br.com.gerador.grammar.ProjetoDSLBaseVisitor;
import br.com.gerador.grammar.ProjetoDSLParser;
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
    private final List<EntityModel> entities;

    public GeradorJavaVisitor() {
        this.entityGenerator = new JavaEntityGenerator();
        this.entities = new ArrayList<>();
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

        // Visit all entities in this package
        for (ProjetoDSLParser.EntityDeclContext entityCtx : ctx.entityDecl()) {
            visitEntityDecl(entityCtx);
        }

        return null;
    }

    @Override
    public Void visitEntityDecl(ProjetoDSLParser.EntityDeclContext ctx) {
        String entityName = ctx.ID().getText();
        System.out.println("Processando entidade: " + entityName);

        // Usar o package base + .entity para as entidades
        String entityPackage = this.basePackage + ".entity";
        EntityModel entity = new EntityModel(entityName, entityPackage);

        // Process fields
        if (ctx.fieldDecl() != null) {
            for (ProjetoDSLParser.FieldDeclContext fieldCtx : ctx.fieldDecl()) {
                FieldModel field = processField(fieldCtx);
                entity.addField(field);
            }
        }

        entities.add(entity);
        generateEntityFile(entity);

        return null;
    }

    private FieldModel processField(ProjetoDSLParser.FieldDeclContext fieldCtx) {
        String fieldName = fieldCtx.ID().getText();
        String fieldType = fieldCtx.type().getText();

        FieldModel field = new FieldModel(fieldName, fieldType);

        // Check if it's optional
        field.setOptional(fieldCtx.option() != null);

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
}
