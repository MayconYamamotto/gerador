service cliente {
  entity Pessoa {
    id: uuid
    nome: string
    email: string?
    idade: integer min(18, "Idade mínima {} anos")
    salario: decimal
    ativo: boolean
    dataNascimento: date
    criadoEm: datetime
  }
  
  entity Endereco {
    id: uuid
    rua: string
    numero: integer
    cidade: string
    cep: string
    pessoaId: uuid
  }
}
