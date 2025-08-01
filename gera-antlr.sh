#!/bin/bash

echo "🔧 Gerando arquivos ANTLR..."

# Caminho do jar
ANTLR_JAR="lib/antlr-4.13.2-complete.jar"

# Diretório base
SRC_DIR="src/main/java/br/com/gerador/grammar"

# Limpar arquivos antigos do ANTLR (se existirem)
echo "🧹 Limpando arquivos antigos..."
rm -rf "$SRC_DIR/.antlr"
find "$SRC_DIR" -name "ProjetoDSL*.java" -not -name "ProjetoDSL.g4" -delete 2>/dev/null || true

# Comando de geração
echo "⚙️ Executando ANTLR..."
java -jar "$ANTLR_JAR" -Dlanguage=Java -visitor -o "$SRC_DIR" "$SRC_DIR/ProjetoDSL.g4"

if [ $? -eq 0 ]; then
    echo "✅ Arquivos ANTLR gerados com sucesso em: $SRC_DIR"
else
    echo "❌ Erro ao gerar arquivos ANTLR"
    exit 1
fi
