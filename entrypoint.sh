#!/bin/bash
set -e

# Use proper directory
cd /opt/datomic

# Export allow others TCP connection 
export JAVA_OPTS="$JAVA_OPTS -Dh2.allowOthers=true"

# Start transactor
bin/transactor config/dev-transactor.properties
