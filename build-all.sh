#!/bin/bash

echo "🏗️ Build completo do gerador DSL"
echo "=================================="

# Arquivo DSL (opcional)
DSL_FILE=${1:-"src/main/java/br/com/gerador/dsl/Teste.dsl"}

echo "1️⃣ Gerando arquivos ANTLR..."
./gera-antlr.sh
if [ $? -ne 0 ]; then
    echo "❌ Falha na geração ANTLR"
    exit 1
fi

echo ""
echo "2️⃣ Compilando projeto..."
./compila.sh
if [ $? -ne 0 ]; then
    echo "❌ Falha na compilação"
    exit 1
fi

echo ""
echo "3️⃣ Gerando código DSL..."
./gera-dsl.sh "$DSL_FILE"
if [ $? -ne 0 ]; then
    echo "❌ Falha na geração DSL"
    exit 1
fi

echo ""
echo "🎉 Build completo executado com sucesso!"
echo "📁 Arquivos gerados em: target/generated-sources/"
