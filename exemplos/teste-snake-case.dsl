package br.com.teste {

  crud {
    generateRepository: true
    generateService: true
    generateController: true
    generateDto: true
    dddLayers: true
  }

  entity ProdutoDigital {
    id: uuid
    nome: string notBlank
    arquivoDownload: string
    formatoArquivo: string
    tamanhoMB: decimal
    dataVencimento: datetime?
    chaveAcesso: string transient
  }

  entity ContaCorrente {
    id: uuid
    numeroAgencia: string notBlank
    numeroConta: string notBlank
    saldoAtual: decimal
    limiteCredito: decimal?
    senhaTransacao: string transient
  }
}
