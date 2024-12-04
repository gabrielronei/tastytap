#!/bin/bash

echo "Aguardando o cluster EKS estar ativo..."
aws eks wait cluster-active --name tastytap-cluster

echo "Atualizando o kubeconfig para o cluster EKS..."S
aws eks update-kubeconfig --name "tastytap-cluster" --region "us-east-1"

echo "Iniciando o deploy..."

FILES=(
  "app-secrets.yaml"
  "app-service.yaml"
  "app-deployment.yaml"
)

for FILE in "${FILES[@]}"; do
  if [ -f "$FILE" ]; then
    echo "Aplicando $FILE..."
    kubectl apply -f "$FILE"
  else
    echo "Arquivo $FILE não encontrado!"
  fi
done

EXTERNAL_IP=$(kubectl get nodes -o wide | grep -v NAME | awk '{print $6}')
echo "EXTERNAL-IP: $EXTERNAL_IP"

echo "Todas as configurações foram aplicadas."