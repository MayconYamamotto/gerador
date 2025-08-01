# Scripts do Gerador DSL

Este diretório contém scripts para automatizar o processo de geração de código a partir de arquivos DSL.

## Scripts Disponíveis

### 🔧 `gerar-antlr.sh`
Gera os arquivos Java do parser ANTLR a partir da gramática.

```bash
./gerar-antlr.sh
```

### 🔨 `compila.sh`
Compila todo o projeto usando Maven.

```bash
./compila.sh
```

### 🚀 `gerar-dsl.sh`
Executa o gerador DSL para processar um arquivo específico.

```bash
# Usar arquivo padrão (Teste.dsl)
./gerar-dsl.sh

# Usar arquivo específico
./gerar-dsl.sh exemplos/sistema-vendas.dsl
./gerar-dsl.sh exemplos/blog-pessoal.dsl
```

### 🏗️ `build-all.sh`
Executa todo o processo: ANTLR → Compilação → Geração DSL.

```bash
# Build completo com arquivo padrão
./build-all.sh

# Build completo com arquivo específico
./build-all.sh exemplos/sistema-vendas.dsl
```

## Estrutura de Arquivos DSL

Os arquivos DSL devem seguir o formato:

```dsl
package br.com.nomeDoSeuProjeto {

  entity NomeDaEntidade {
    campo1: tipo
    campo2: tipo?  // opcional
    campo3: tipo validation
  }
}
```

### Tipos Suportados
- `string` - String
- `uuid` - UUID
- `integer` - Integer
- `long` - Long
- `double` - Double
- `boolean` - Boolean
- `date` - LocalDate
- `datetime` - LocalDateTime
- `decimal` - BigDecimal

### Validações Suportadas
- `notNull` - @NotNull
- `notBlank` - @NotBlank
- `min(numero, "mensagem")` - @Min
- `max(numero)` - @Max

## Saída

Os arquivos Java são gerados em:
```
target/generated-sources/[package]/entity/[NomeEntidade].java
```

Por exemplo, para `package br.com.meuProjeto`, as entidades ficam em:
```
target/generated-sources/br/com/meuProjeto/entity/
```
