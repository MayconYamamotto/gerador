service cliente {
  entity Pessoa {
    id: uuid
    nome: string?
    idade: integer min(18, "Idade mínima {} anos")
  }
}
