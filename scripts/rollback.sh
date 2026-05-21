#!/bin/bash
APP_NAME=$1
PREVIOUS_VERSION=$2

echo "=== ServerSync Rollback ==="
echo "App: $APP_NAME"
echo "Rolling back to: $PREVIOUS_VERSION"
echo "Date: $(date)"

cd /opt/$APP_NAME

# Stop current version
echo "Stopping current version..."
docker-compose down

# Checkout previous version
echo "Checking out previous version..."
git checkout $PREVIOUS_VERSION

# Restart with previous version
echo "Starting previous version..."
docker-compose up -d --build

echo "Rollback complete!"
echo "=========================="