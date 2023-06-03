# USAGE: bash build.sh <flashcards/learn/rewards/users/ui>

NAME=$1

docker build -t $NAME ./$NAME/ &&
docker image tag $NAME:latest ghcr.io/wiktoor/$NAME:latest &&
docker image push ghcr.io/wiktoor/$NAME:latest