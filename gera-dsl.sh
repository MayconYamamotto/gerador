#!/bin/bash

echo "🚀 Executando gerador DSL..."

# Arquivo DSL padrão
DSL_FILE=${1:-"src/main/java/br/com/gerador/dsl/Teste.dsl"}

# Verificar se o arquivo existe
if [ ! -f "$DSL_FILE" ]; then
    echo "❌ Arquivo DSL não encontrado: $DSL_FILE"
    echo "💡 Uso: $0 [arquivo.dsl]"
    echo "💡 Exemplo: $0 exemplo-vendas.dsl"
    exit 1
fi

echo "📄 Processando arquivo: $DSL_FILE"

# Executar o gerador
java -cp "target/classes;lib/antlr-4.13.2-complete.jar" br.com.gerador.Main "$DSL_FILE"

if [ $? -eq 0 ]; then
    echo "✅ Código gerado com sucesso!"
    echo "📁 Verifique os arquivos em: target/generated-sources/"
else
    echo "❌ Erro na geração de código"
    exit 1
fi
