# Funcionalidades Implementadas: Campos Transient e Migração Flyway

## ✅ Resumo das Implementações

### 1. **Atributo `transient` na DSL**
- **Localização**: `src/main/java/br/com/gerador/grammar/ProjetoDSL.g4`
- **Modificação**: Adicionada regra `modifier: 'transient';` na gramática ANTLR
- **Uso**: `campo: tipo transient` ou `campo: tipo? transient`

### 2. **Modelo de Campo Atualizado**
- **Localização**: `src/main/java/br/com/gerador/model/FieldModel.java`
- **Adição**: Campo `transientField` com getters/setters correspondentes
- **Funcionalidade**: Armazena informação se o campo é transient

### 3. **Processamento no Visitor**
- **Localização**: `src/main/java/br/com/gerador/visitor/GeradorJavaVisitor.java`
- **Modificação**: Método `processField()` detecta e processa o modificador `transient`
- **Integração**: Chama o gerador de migração Flyway para cada entidade

### 4. **Gerador de Migração Flyway**
- **Localização**: `src/main/java/br/com/gerador/generator/FlywayMigrationGenerator.java`
- **Funcionalidade**: 
  - Cria scripts SQL de migração automaticamente
  - Exclui campos marcados como `transient`
  - Mapeia tipos DSL para tipos SQL
  - Adiciona constraints baseadas nas validações
  - Nomeia arquivos seguindo convenção Flyway: `V{timestamp}__Create_{table}_table.sql`

### 5. **TypeMapper Estendido**
- **Localização**: `src/main/java/br/com/gerador/util/TypeMapper.java`
- **Adição**: Método `mapSqlType()` para mapeamento DSL → SQL
- **Mapeamentos**:
  - `string` → `VARCHAR(255)`
  - `uuid` → `UUID`
  - `integer` → `INTEGER`
  - `long` → `BIGINT`
  - `double` → `DOUBLE`
  - `boolean` → `BOOLEAN`
  - `date` → `DATE`
  - `datetime` → `TIMESTAMP`
  - `decimal` → `DECIMAL(19,2)`

### 6. **Gerador de Entidade Atualizado**
- **Localização**: `src/main/java/br/com/gerador/generator/JavaEntityGenerator.java`
- **Modificação**: Campos `transient` recebem anotação `@Transient` do JPA
- **Resultado**: Campos não são persistidos no banco, mas estão disponíveis em runtime

## 🧪 Exemplos de Teste

### **DSL de Exemplo**
```yaml
entity Usuario {
  id: uuid
  nome: string notBlank
  email: string notBlank
  senha: string transient        // Não persiste no banco
  ativo: boolean
  tokenTemp: string? transient   // Campo opcional e transient
  criadoEm: datetime
}
```

### **Resultado - Entidade Java**
```java
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    
    @Column(name = "nome", nullable = false)
    @NotBlank
    private String nome;
    
    @Column(name = "email", nullable = false)
    @NotBlank
    private String email;
    
    @Transient  // ← Campo transient
    private String senha;
    
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
    
    @Transient  // ← Campo transient
    private String tokenTemp;
    
    @Column(name = "criadoem", nullable = false)
    private LocalDateTime criadoEm;
}
```

### **Resultado - Migração Flyway**
```sql
-- V20250801224010__Create_usuario_table.sql
CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    criado_em TIMESTAMP NOT NULL
);
-- Note que 'senha' e 'tokenTemp' NÃO estão presentes
```

## 🚀 Como Usar

1. **Definir campos transient na DSL**:
   ```yaml
   entity MinhaEntidade {
     campoNormal: string
     campoTransient: string transient
     campoOpcionalTransient: string? transient
   }
   ```

2. **Executar o gerador**:
   ```bash
   bash gerar-dsl.sh exemplos/meu-arquivo.dsl
   ```

3. **Verificar os arquivos gerados**:
   - **Entidades**: `target/generated-sources/{package}/domain/entity/`
   - **Migrações**: `target/generated-sources/src/main/resources/db/migration/`

## ✨ Benefícios

- **Flexibilidade**: Campos disponíveis em runtime mas não persistidos
- **Segurança**: Dados sensíveis (senhas, tokens) não vão para o banco via JPA
- **Automação**: Migrações Flyway geradas automaticamente
- **Consistência**: Schema do banco sempre alinhado com as entidades
- **Manutenibilidade**: Mudanças na DSL refletem automaticamente no banco
