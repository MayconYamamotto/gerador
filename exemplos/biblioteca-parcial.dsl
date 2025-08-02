package br.com.biblioteca {

  crud {
    generateRepository: true
    generateService: true
    generateController: false
    generateDto: true
    dddLayers: true
  }

  entity Livro {
    id: uuid
    titulo: string notBlank
    autor: string notBlank
    isbn: string notBlank
    preco: decimal min(0, "Preço deve ser positivo")
    disponivel: boolean
    criadoEm: datetime
    atualizadoEm: datetime?
  }

  entity Autor {
    id: uuid
    nome: string notBlank
    biografia: string?
    ativo: boolean
    criadoEm: datetime
  }
}
