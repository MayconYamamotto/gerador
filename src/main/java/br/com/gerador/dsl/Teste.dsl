package br.com.projetoX {

  entity Cliente {
    id: uuid
    nome: string
    email: string?
    idade: integer
    salario: decimal
    ativo: boolean
    dataNascimento: date
    criadoEm: datetime
  }

  entity Produto {
    id: uuid
    nome: string
    preco: decimal
    categoria: string
    ativo: boolean
    criadoEm: datetime
  }

  entity Pedido {
  id: uuid
  numero: string
  valor: decimal
  status: string
  criadoEm: datetime
  }
}
