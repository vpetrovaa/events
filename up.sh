cd src/infra/

kubectl apply -f pg-secrets.yml
kubectl apply -f pg-configmap.yml
kubectl apply -f pg-stateful.yml
kubectl apply -f pg-service.yml
kubectl apply -f minio-secrets.yml
kubectl apply -f minio-configmap.yml
kubectl apply -f minio-stateful.yml
kubectl apply -f minio-service.yml
kubectl apply -f events-secrets.yml
kubectl apply -f events-configmap.yml
kubectl apply -f events-deployment.yml
kubectl apply -f events-service.yml