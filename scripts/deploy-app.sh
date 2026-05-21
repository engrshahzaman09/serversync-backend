#!/bin/bash
REPO_URL=$1
BRANCH=$2
APP_NAME=$3

echo "=== ServerSync Deployment ==="
echo "Repo: $REPO_URL"
echo "Branch: $BRANCH"
echo "App: $APP_NAME"

echo "Pulling code..."
git clone -b $BRANCH $REPO_URL /opt/$APP_NAME

cd /opt/$APP_NAME
echo "Building Docker image..."
docker-compose up -d --build

echo "Deployment complete!"
echo "============================="