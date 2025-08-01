package br.com.sistemaVendas {

  entity Usuario {
    id: uuid
    nome: string
    email: string
    senha: string
    ativo: boolean
    criadoEm: datetime
  }

  entity Categoria {
    id: uuid
    nome: string
    descricao: string?
    ativa: boolean
  }

  entity Item {
    id: uuid
    nome: string
    descricao: string?
    preco: decimal
    categoria: string
    estoque: integer
  }
}
