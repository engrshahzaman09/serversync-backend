#!/bin/bash
echo "=== ServerSync Health Check ==="
echo "Date: $(date)"
echo "Uptime: $(uptime)"
echo "CPU Usage: $(top -bn1 | grep 'Cpu(s)' | awk '{print $2}')%"
echo "RAM Used: $(free -m | awk 'NR==2{printf "%s/%sMB\n", $3,$2}')"
echo "Disk Usage: $(df -h / | awk 'NR==2{print $5}')"
echo "================================"