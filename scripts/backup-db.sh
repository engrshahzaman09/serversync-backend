#!/bin/bash
DB_NAME=$1
DB_USER=$2
DB_PASS=$3
BACKUP_DIR="/opt/backups"

echo "=== ServerSync DB Backup ==="
echo "Database: $DB_NAME"
echo "Date: $(date)"

mkdir -p $BACKUP_DIR

FILENAME="$BACKUP_DIR/${DB_NAME}_$(date +%Y%m%d_%H%M%S).sql"

mysqldump -u$DB_USER -p$DB_PASS $DB_NAME > $FILENAME

echo "Backup saved: $FILENAME"
echo "============================"