# Projeto DSL Autocomplete Extension

Uma extensão para VS Code que fornece autocomplete, syntax highlighting e outras funcionalidades para a linguagem DSL do Projeto.

## Funcionalidades

### 🚀 Autocomplete Inteligente
- **Keywords**: `service`, `entity`
- **Tipos de dados**: `string`, `uuid`, `integer`, `long`, `double`, `boolean`, `date`, `datetime`, `decimal`
- **Validações**: `min()`, `max()`, `notNull`, `notBlank`
- **Sugestões contextuais**: A extensão entende o contexto e sugere apenas opções relevantes

### 🎨 Syntax Highlighting
- Destaque de sintaxe para todos os elementos da DSL
- Cores diferenciadas para keywords, tipos, validações e comentários

### 📝 Snippets
- `service` - Cria uma declaração de serviço
- `entity` - Cria uma declaração de entidade com campo id
- `field-*` - Vários snippets para diferentes tipos de campos
- `field-min`, `field-max` - Campos com validações

### 🔍 Hover Documentation
- Documentação ao passar o mouse sobre keywords e tipos
- Explicações claras sobre validações

### ⚡ Formatação Automática
- Formatação automática de código DSL
- Espaçamento consistente

## Instalação

### Opção 1: Instalação Local (Desenvolvimento)

1. Clone ou copie o projeto da extensão
2. Abra a pasta `dsl-autocomplete-extension` no VS Code
3. Instale as dependências:
   ```bash
   npm install
   ```
4. Compile o projeto:
   ```bash
   npm run compile
   ```
5. Pressione `F5` para abrir uma nova janela do VS Code com a extensão carregada

### Opção 2: Empacotar e Instalar

1. Instale o `vsce` (VS Code Extension CLI):
   ```bash
   npm install -g vsce
   ```

2. Na pasta da extensão, execute:
   ```bash
   vsce package
   ```

3. Instale o arquivo `.vsix` gerado:
   ```bash
   code --install-extension projeto-dsl-autocomplete-0.1.0.vsix
   ```

## Uso

### Exemplo de Arquivo DSL

```dsl
service cliente {
    entity Pessoa {
        id: uuid
        nome: string notBlank
        email: string?
        idade: integer min(18, "Idade mínima {} anos")
        salario: decimal
        ativo: boolean
        dataNascimento: date
        criadoEm: datetime
    }
    
    entity Endereco {
        id: uuid
        rua: string notBlank
        numero: integer
        cidade: string notBlank
        cep: string
        pessoaId: uuid
    }
}
```

### Autocomplete em Ação

1. **Digite `service`** - A extensão sugere a estrutura completa do serviço
2. **Digite `entity`** - Sugere a estrutura da entidade com campo id
3. **Após `:` em um campo** - Lista todos os tipos disponíveis
4. **Após um tipo** - Sugere `?` para opcional ou validações
5. **Campo vazio** - Sugere nomes comuns de campos

### Shortcuts Úteis

- `Ctrl+Space` - Forçar autocomplete
- `Ctrl+Shift+P` > "Format Document" - Formatar o arquivo
- Hover sobre qualquer keyword para ver documentação

## Estrutura do Projeto

```
dsl-autocomplete-extension/
├── src/
│   ├── extension.ts           # Ponto de entrada da extensão
│   └── language/
│       └── completionProvider.ts  # Lógica do autocomplete
├── syntaxes/
│   └── dsl.tmLanguage.json    # Definição de syntax highlighting
├── snippets/
│   └── snippets.code-snippets # Snippets predefinidos
├── package.json               # Configuração da extensão
└── tsconfig.json             # Configuração TypeScript
```

## Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## Licença

Este projeto está licenciado sob a licença MIT.

## Roadmap

- [ ] Validação de sintaxe em tempo real
- [ ] Suporte a importação entre arquivos
- [ ] IntelliSense mais avançado
- [ ] Refactoring automático
- [ ] Integração com Language Server Protocol
- [ ] Suporte a debugging