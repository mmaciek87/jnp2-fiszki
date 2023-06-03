# USAGE: bash build.sh <flashcards/learn/rewards/users/ui> <github name>

NAME=$1
USER=$2

docker build -t $NAME ./$NAME/ &&
docker image tag $NAME:latest ghcr.io/$USER/$NAME:latest &&
docker image push ghcr.io/$USER/$NAME:latest