#!/bin/bash

FILES=(
  "mysql-secrets.yaml"
  "mysql-deployment.yaml"
  "app-secrets.yaml"
  "app-configmap.yaml"
  "app-autoscaling-config.yaml"
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