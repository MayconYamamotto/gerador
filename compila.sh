#!/bin/bash

echo "🔨 Compilando o projeto..."

# Compilar usando Maven
mvn clean compile -q

if [ $? -eq 0 ]; then
    echo "✅ Projeto compilado com sucesso!"
else
    echo "❌ Erro na compilação"
    exit 1
fi
