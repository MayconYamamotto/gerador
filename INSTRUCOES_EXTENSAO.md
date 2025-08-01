# Como Fazer uma Extensão de Autocomplete para sua DSL

Você já tem uma boa estrutura inicial para a extensão! Aqui está um guia completo de como finalizar e usar:

## 📁 Estrutura da Extensão

Sua extensão já tem os componentes principais:

```
dsl-autocomplete-extension/
├── package.json                    # ✅ Configurado
├── tsconfig.json                   # ✅ Configurado
├── language-configuration.json    # ✅ Criado
├── README.md                       # ✅ Documentado
├── src/
│   ├── extension.ts               # ✅ Melhorado
│   └── language/
│       └── completionProvider.ts  # ✅ Muito melhorado
├── syntaxes/
│   └── dsl.tmLanguage.json        # ✅ Melhorado
└── snippets/
    └── snippets.code-snippets     # ✅ Criado
```

## 🚀 Como Instalar e Usar

### Método 1: Desenvolvimento Local (Recomendado)

1. **Abra o VS Code** na pasta `dsl-autocomplete-extension`

2. **Instale as dependências** (se o npm funcionar):
   ```bash
   npm install
   ```

3. **Ou instale manualmente** as dependências TypeScript:
   - Abra o terminal integrado do VS Code
   - Execute: `npm init -y` (se não tiver package.json)
   - Execute: `npm install --save-dev @types/vscode @types/node typescript`

4. **Teste a extensão**:
   - Pressione `F5` no VS Code
   - Uma nova janela do VS Code será aberta com a extensão carregada
   - Crie um arquivo `.dsl` para testar

### Método 2: Instalação Definitiva

1. **Instale o VSCE** (VS Code Extension CLI):
   ```bash
   npm install -g vsce
   ```

2. **Empacote a extensão**:
   ```bash
   vsce package
   ```

3. **Instale o arquivo .vsix**:
   ```bash
   code --install-extension projeto-dsl-autocomplete-0.1.0.vsix
   ```

## 🎯 Funcionalidades Implementadas

### 1. **Autocomplete Inteligente**
- Sugere `service` no início do arquivo
- Sugere `entity` dentro de services
- Lista tipos após `:` em campos
- Sugere validações após tipos
- Sugere `?` para campos opcionais
- Sugere nomes comuns de campos

### 2. **Syntax Highlighting**
- Keywords (`service`, `entity`) destacados
- Tipos de dados coloridos
- Validações com cores específicas
- Strings e números formatados
- Comentários (`//`) suportados

### 3. **Snippets Prontos**
- `service` → Cria estrutura completa do serviço
- `entity` → Cria entidade com campo id
- `field-string`, `field-int`, etc. → Campos específicos
- `field-min`, `field-max` → Campos com validações

### 4. **Hover Documentation**
- Documentação ao passar mouse sobre keywords
- Explicações sobre tipos e validações

### 5. **Formatação**
- Comando "Format Document" disponível
- Espaçamento automático corrigido

## 🧪 Testando a Extensão

1. **Crie um arquivo** `teste.dsl`

2. **Digite e teste**:
```dsl
service  # <- deve sugerir autocomplete
```

3. **Continue testando**:
```dsl
service cliente {
    entity  # <- deve sugerir autocomplete
}
```

4. **Teste campos**:
```dsl
service cliente {
    entity Pessoa {
        nome:  # <- deve listar tipos
        idade: integer  # <- deve sugerir ? ou validações
    }
}
```

## 🔧 Personalizações Adicionais

### Para adicionar novos tipos:
Edite `src/language/completionProvider.ts`:
```typescript
private types: string[] = [
    'string', 'uuid', 'integer', // ... tipos existentes
    'novoTipo'  // <- adicione aqui
];
```

### Para adicionar novas validações:
```typescript
private validations: { [key: string]: string } = {
    // ... validações existentes
    'novaValidacao': 'novaValidacao(${1:param})'
};
```

### Para adicionar novos snippets:
Edite `snippets/snippets.code-snippets`:
```json
{
    "Novo Snippet": {
        "prefix": "prefixo",
        "body": ["código do snippet"],
        "description": "Descrição"
    }
}
```

## 🎨 Customizando Cores

Edite `syntaxes/dsl.tmLanguage.json` para alterar o highlighting:
- `keyword.control` → Cor dos keywords
- `storage.type` → Cor dos tipos
- `support.function` → Cor das validações

## 📝 Exemplo Completo de Uso

Crie um arquivo `exemplo.dsl`:

```dsl
service ecommerce {
    entity Produto {
        id: uuid
        nome: string notBlank
        preco: decimal min(0, "Preço deve ser positivo")
        descricao: string?
        ativo: boolean
        categoria: string
        estoque: integer max(9999)
        criadoEm: datetime
    }
    
    entity Pedido {
        id: uuid
        clienteId: uuid
        produtoId: uuid
        quantidade: integer min(1, "Quantidade mínima é 1")
        total: decimal
        status: string notBlank
        dataPedido: datetime
    }
}
```

## ⚡ Benefícios da Extensão

1. **Produtividade**: Autocomplete reduz erros de digitação
2. **Consistência**: Syntax highlighting melhora legibilidade
3. **Documentação**: Hover mostra informações úteis
4. **Rapidez**: Snippets aceleram criação de estruturas
5. **Qualidade**: Validação visual de sintaxe

## 🔄 Melhorias Futuras

Você pode expandir a extensão com:
- Validação de sintaxe em tempo real
- IntelliSense mais avançado
- Suporte a múltiplos arquivos
- Integração com seu gerador Java
- Refactoring automático
- Debugging support

A extensão está pronta para uso! Teste-a e veja como ela melhora sua experiência ao escrever arquivos DSL.
