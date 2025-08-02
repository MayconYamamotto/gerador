package br.com.exemplo {

  crud {
    generateRepository: true
    generateService: true
    generateController: true
    generateDto: true
    dddLayers: true
  }

  entity Usuario {
    id: uuid
    nome: string notBlank
    email: string notBlank
    senha: string transient
    ativo: boolean
    criadoEm: datetime
    tokenTemporario: string? transient
  }

  entity Produto {
    id: uuid
    nome: string notBlank
    preco: decimal
    categoria: string
    descricao: string?
    calculoInterno: string transient
  }
}
