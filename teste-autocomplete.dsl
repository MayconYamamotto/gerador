// Teste da extensão DSL
service testeExtensao {
    entity TesteEntity {
        id: uuid
        nome: string
        
        // Tente digitar aqui para testar autocomplete:
        // 1. Digite "email: " e veja se sugere tipos
        // 2. Digite "idade: integer " e veja se sugere validações
        // 3. Digite em linha vazia e veja se sugere nomes de campos
    }
}
