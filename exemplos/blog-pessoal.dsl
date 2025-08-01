package br.com.blogPessoal {

  entity Autor {
    id: uuid
    nome: string
    email: string
    bio: string?
    ativo: boolean
    criadoEm: datetime
  }

  entity Post {
    id: uuid
    titulo: string
    conteudo: string
    resumo: string?
    publicado: boolean
    dataPublicacao: datetime?
    criadoEm: datetime
  }

  entity Comentario {
    id: uuid
    texto: string
    aprovado: boolean
    criadoEm: datetime
  }
}
