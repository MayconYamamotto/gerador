package br.com.simples {

  entity Usuario {
    id: uuid
    nome: string notBlank
    email: string notBlank
    ativo: boolean
    criadoEm: datetime
  }
}
