package cliente {

  entity Pessoa {
    id: uuid
    nome: string
    email: string?
    idade: integer
    salario: decimal
    ativo: boolean
    dataNascimento: date
    criadoEm: datetime
  }

  entity Caixa {
    id: uuid
    dataAbertura: datetime
    dataFechamento: datetime?
    valorAbertura: decimal
    valorFechamento: decimal?
  }

  entity Paulinha {
  id: uuid
  nome: string
  }
}
