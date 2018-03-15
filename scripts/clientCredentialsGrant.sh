#!/usr/bin/env bash
echo "##### CLIENT CREDENTIALS GRANT #####"
curl localhost:8080/auth/oauth/token -d grant_type=client_credentials -d scope=write -u $1:$2 -v
echo
echo