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
