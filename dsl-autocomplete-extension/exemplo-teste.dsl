service teste {
    entity ExemploEntity {
        id: uuid
        nome: string notBlank
        idade: integer min(18, "Idade mínima 18 anos")
        email: string?
        ativo: boolean
        dataCriacao: datetime
    }

    entity Tente {
      asd: 
    }
}
