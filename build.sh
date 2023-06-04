# USAGE: bash build.sh <flashcards/learn/rewards/users/ui>

NAME=$1
NAMESPACE=wiktoor

docker build --platform linux/amd64 -t $NAME ./$NAME/ &&
docker image tag $NAME:latest ghcr.io/$NAMESPACE/$NAME:latest &&
docker image push ghcr.io/$NAMESPACE/$NAME:latest
