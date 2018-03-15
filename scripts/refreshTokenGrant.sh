#!/usr/bin/env bash
echo "##### REFRESH TOKEN #####"
curl localhost:8080/auth/oauth/token -d grant_type=refresh_token -d refresh_token=$3 -u $1:$2 -v
echo
echo