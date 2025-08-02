#!/bin/bash

echo "🔧 Gerando arquivos ANTLR..."

# Caminho do jar
ANTLR_JAR="lib/antlr-4.13.2-complete.jar"

# Diretórios
GRAMMAR_DIR="src/main/java/br/com/gerador/grammar"
OUTPUT_DIR="src/main/java/br/com/gerador/grammar/antlr4"

# Criar diretório de saída se não existir
mkdir -p "$OUTPUT_DIR"

# Limpar arquivos antigos do ANTLR (se existirem)
echo "🧹 Limpando arquivos antigos..."
rm -rf "$OUTPUT_DIR"/*.java
rm -rf "$GRAMMAR_DIR/.antlr"

# Comando de geração
echo "⚙️  Executando ANTLR..."
java -jar "$ANTLR_JAR" -Dlanguage=Java -visitor -o "$OUTPUT_DIR" "$GRAMMAR_DIR/ProjetoDSL.g4"

if [ $? -eq 0 ]; then
    echo "✅ Arquivos ANTLR gerados com sucesso em: $OUTPUT_DIR"
    echo "📂 Para que o Maven reconheça os arquivos, adicione o diretório ao classpath:"
    echo "   src/main/java/br/com/gerador/grammar/antlr4"
else
    echo "❌ Erro ao gerar arquivos ANTLR"
    exit 1
fi
