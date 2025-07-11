#!/bin/bash

# Caminho do jar
ANTLR_JAR="lib/antlr-4.13.2-complete.jar"

# Diretório base
SRC_DIR="src/main/java/br/com/gerador/grammar"

# Comando de geração
java -jar "$ANTLR_JAR" -Dlanguage=Java -visitor -o "$SRC_DIR" "$SRC_DIR/MinhaLinguagem.g4"

echo "✅ Arquivos ANTLR gerados em: $SRC_DIR"
