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
    ativo: boolean
    senha: string transient
    token: string? transient
    criadoEm: datetime
  }

  entity Produto {
    id: uuid
    nome: string notBlank
    descricao: string?
    preco: decimal notNull
    categoria: string notBlank
    estoque: integer
    ativo: boolean
    margemLucro: decimal? transient
    calculoFrete: string? transient
    criadoEm: datetime
    atualizadoEm: datetime?
  }

  entity Pedido {
    id: uuid
    numero: string notBlank
    dataCompra: datetime
    valorTotal: decimal
    status: string notBlank
    observacoes: string?
    desconto: decimal? transient
    comissaoVendedor: decimal? transient
  }
}
