# Gerador de CRUD DDD

Um gerador de código que cria automaticamente aplicações Spring Boot seguindo os princípios de **Domain-Driven Design (DDD)** a partir de uma DSL (Domain Specific Language) simples e intuitiva.

## 🚀 Funcionalidades

- **Geração de CRUD Completo**: Create, Read, Update, Delete com soft delete
- **Arquitetura DDD**: Estrutura organizada em camadas Domain, Application e Interface
- **REST APIs**: Controllers RESTful com documentação Swagger
- **Validações**: Bean Validation com mensagens personalizadas
- **Paginação**: Suporte completo à paginação com Spring Data
- **Busca Avançada**: Métodos de busca customizados
- **Auditoria**: Campos de criação e atualização automáticos
- **Transações**: Suporte a transações declarativas
- **Campos Transient**: Suporte a campos que não são persistidos no banco de dados
- **Migração Flyway**: Geração automática de scripts de migração de banco de dados

## 🆕 Novas Funcionalidades

### 🔄 Campos Transient
Agora é possível marcar campos como `transient` na DSL. Esses campos:
- Não são incluídos nas migrações Flyway
- São marcados com `@Transient` na entidade JPA
- Estão disponíveis para uso em tempo de execução/lógica de aplicação

**Exemplo de uso:**
```yaml
entity Usuario {
  id: uuid
  nome: string notBlank
  email: string notBlank
  senha: string transient        // Não persiste no banco
  ativo: boolean
  tokenTemp: string? transient   // Campo opcional e transient
}
```

### 🗃️ Migração Flyway
O gerador agora cria automaticamente scripts de migração Flyway que:
- Refletem a estrutura das entidades definidas na DSL
- Excluem campos marcados como `transient`
- Incluem constraints baseadas nas validações definidas
- Seguem as convenções de nomenclatura do Flyway

**Arquivos gerados:**
- `src/main/resources/db/migration/V{timestamp}__Create_{table}_table.sql`

## 🏗️ Arquitetura DDD Gerada

### Domain Layer (Domínio)
- **Entities**: Entidades JPA com anotações de validação
- **Repositories**: Interfaces de repositório com métodos customizados

### Application Layer (Aplicação)
- **DTOs**: Create, Update e Response DTOs
- **Services**: Serviços de aplicação com regras de negócio

### Interface Layer (Interface)
- **Controllers**: REST Controllers com endpoints completos

## 🔧 Scripts Disponíveis

### 🏗️ `build-all.sh` (Recomendado)
Executa todo o processo: ANTLR → Compilação → Geração DSL.

```bash
# Build completo com arquivo específico
./build-all.sh exemplos/ecommerce-ddd.dsl
./build-all.sh exemplos/blog-pessoal.dsl
```
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
# Usar arquivo específico
./gerar-dsl.sh exemplos/ecommerce-ddd.dsl
```

## 📝 Sintaxe da DSL

### Estrutura Básica

```dsl
package br.com.meuProjeto {

  // Configuração do CRUD (opcional)
  crud {
    generateRepository: true
    generateService: true
    generateController: true
    generateDto: true
    dddLayers: true
  }

  entity MinhaEntidade {
    id: uuid
    nome: string notBlank
    email: string notBlank
    idade: integer min(0, "Idade deve ser positiva")
    ativo: boolean
    criadoEm: datetime
    atualizadoEm: datetime?
  }
}
```

### Configuração de CRUD

| Opção | Descrição | Padrão |
|-------|-----------|--------|
| `generateRepository` | Gera interfaces de repositório | `true` |
| `generateService` | Gera serviços de aplicação | `true` |
| `generateController` | Gera controllers REST | `true` |
| `generateDto` | Gera DTOs (Create, Update, Response) | `true` |
| `dddLayers` | Organiza em camadas DDD | `true` |

### Tipos de Dados Suportados

| Tipo DSL | Tipo Java | Descrição |
|----------|-----------|-----------|
| `string` | `String` | Texto |
| `uuid` | `UUID` | Identificador único |
| `integer` | `Integer` | Número inteiro |
| `long` | `Long` | Número inteiro longo |
| `double` | `Double` | Número decimal |
| `boolean` | `Boolean` | Verdadeiro/Falso |
| `date` | `LocalDate` | Data |
| `datetime` | `LocalDateTime` | Data e hora |
| `decimal` | `BigDecimal` | Decimal de precisão |

### Validações Suportadas

| Validação | Anotação Java | Exemplo |
|-----------|---------------|---------|
| `notNull` | `@NotNull` | `nome: string notNull` |
| `notBlank` | `@NotBlank` | `email: string notBlank` |
| `min(valor, "msg")` | `@Min` | `idade: integer min(0, "Deve ser positivo")` |
| `max(valor)` | `@Max` | `nota: integer max(10)` |

### Campos Opcionais

Use `?` para tornar um campo opcional:
```dsl
telefone: string?    // Campo opcional
endereco: string?    // Campo opcional
```

## 📁 Estrutura de Arquivos Gerada

```
target/generated-sources/
└── br/com/[package]/
    ├── domain/
    │   ├── entity/              # Entidades JPA
    │   │   ├── Cliente.java
    │   │   ├── Produto.java
    │   │   └── Pedido.java
    │   └── repository/          # Interfaces de repositório
    │       ├── ClienteRepository.java
    │       ├── ProdutoRepository.java
    │       └── PedidoRepository.java
    ├── application/
    │   ├── dto/                 # DTOs
    │   │   ├── CreateClienteDto.java
    │   │   ├── UpdateClienteDto.java
    │   │   ├── ClienteResponseDto.java
    │   │   └── ...
    │   └── service/             # Serviços de aplicação
    │       ├── ClienteService.java
    │       ├── ProdutoService.java
    │       └── PedidoService.java
    └── interfaces/
        └── controller/          # Controllers REST
            ├── ClienteController.java
            ├── ProdutoController.java
            └── PedidoController.java
```

## 🌟 Exemplos Práticos

### Exemplo 1: E-commerce Completo

```dsl
package br.com.ecommerce {

  crud {
    generateRepository: true
    generateService: true
    generateController: true
    generateDto: true
    dddLayers: true
  }

  entity Cliente {
    id: uuid
    nome: string notBlank
    email: string notBlank
    cpf: string notBlank
    telefone: string?
    endereco: string?
    ativo: boolean
    criadoEm: datetime
    atualizadoEm: datetime?
  }

  entity Produto {
    id: uuid
    nome: string notBlank
    descricao: string?
    preco: decimal min(0, "Preço deve ser maior que zero")
    categoria: string notBlank
    estoque: integer min(0, "Estoque não pode ser negativo")
    ativo: boolean
    criadoEm: datetime
    atualizadoEm: datetime?
  }

  entity Pedido {
    id: uuid
    numero: string notBlank
    valorTotal: decimal min(0, "Valor total deve ser maior que zero")
    status: string notBlank
    dataEntrega: date?
    observacoes: string?
    ativo: boolean
    criadoEm: datetime
    atualizadoEm: datetime?
  }
}
```

### Exemplo 2: Configuração Personalizada

```dsl
package br.com.biblioteca {

  crud {
    generateRepository: true
    generateService: true
    generateController: false    // Não gerar controllers
    generateDto: true
    dddLayers: true
  }

  entity Livro {
    id: uuid
    titulo: string notBlank
    autor: string notBlank
    isbn: string notBlank
    preco: decimal min(0, "Preço deve ser positivo")
    disponivel: boolean
    criadoEm: datetime
    atualizadoEm: datetime?
  }
}
```

## 🚀 Como Usar

1. **Crie seu arquivo DSL** em `exemplos/meu-projeto.dsl`

2. **Execute o build completo**:
   ```bash
   ./build-all.sh exemplos/meu-projeto.dsl
   ```

3. **Verifique os arquivos gerados** em `target/generated-sources/`

4. **Copie os arquivos** para seu projeto Spring Boot

## 🔍 Funcionalidades dos Componentes Gerados

### Entities (Entidades)
- Anotações JPA (`@Entity`, `@Table`, `@Column`)
- Anotações Lombok (`@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`)
- Validações Bean Validation (`@NotNull`, `@NotBlank`, `@Min`, `@Max`)
- Configuração automática de IDs (UUID com geração automática)

### Repositories (Repositórios)
- Interface estendendo `JpaRepository<Entity, UUID>`
- Métodos customizados:
  - `findAllAtivos()` - Busca registros ativos
  - `findByIdAndAtivoTrue(UUID id)` - Busca por ID ativo
  - `findByNomeContainingIgnoreCaseAndAtivoTrue(String nome)` - Busca por nome (se existir campo nome)

### Services (Serviços)
- Métodos CRUD completos:
  - `create(CreateDto dto)` - Criar novo registro
  - `update(UUID id, UpdateDto dto)` - Atualizar registro
  - `findById(UUID id)` - Buscar por ID
  - `findAll()` - Listar todos ativos
  - `findAll(Pageable pageable)` - Listar com paginação
  - `delete(UUID id)` - Exclusão lógica (soft delete)
  - `activate(UUID id)` - Reativar registro
  - `searchByNome(String nome)` - Buscar por nome
- Conversores automáticos entre DTOs e Entities
- Auditoria automática (campos de criação/atualização)

### Controllers (Controladores)
- Endpoints REST completos:
  - `POST /api/entidades` - Criar
  - `PUT /api/entidades/{id}` - Atualizar
  - `GET /api/entidades/{id}` - Buscar por ID
  - `GET /api/entidades` - Listar todos
  - `GET /api/entidades/paginated` - Listar com paginação
  - `DELETE /api/entidades/{id}` - Deletar (soft delete)
  - `PATCH /api/entidades/{id}/activate` - Reativar
  - `GET /api/entidades/search?nome=` - Buscar por nome
- Documentação Swagger automática
- Tratamento de erros com status HTTP apropriados
- Validação automática de DTOs

### DTOs (Data Transfer Objects)
- **CreateDto**: Para criação (sem ID e campos de auditoria)
- **UpdateDto**: Para atualização (com ID, sem campos de auditoria)
- **ResponseDto**: Para resposta (todos os campos)
- Validações Bean Validation apropriadas
- Mensagens de erro personalizadas

## 📋 Requisitos

- Java 17+
- Maven 3.8+
- ANTLR 4.13.2

## 🛠️ Tecnologias Utilizadas

- **ANTLR 4**: Parser para a DSL
- **Spring Boot**: Framework base para os componentes gerados
- **Spring Data JPA**: Para os repositórios
- **Bean Validation**: Para validações
- **Lombok**: Para redução de boilerplate
- **Swagger/OpenAPI**: Para documentação das APIs

## 📝 Notas Importantes

- **Soft Delete**: Por padrão, a exclusão é lógica usando o campo `ativo`
- **Auditoria**: Campos `criadoEm` e `atualizadoEm` são preenchidos automaticamente
- **UUID**: IDs são sempre do tipo UUID com geração automática
- **Transações**: Operações de escrita são executadas em transações
- **Validações**: Mensagens de erro em português por padrão

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Suporte

Se você encontrar algum problema ou tiver sugestões, por favor abra uma [issue](https://github.com/MayconYamamotto/gerador/issues).

---
