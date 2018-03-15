#!/usr/bin/env bash
echo "##### PASSWORD GRANT #####"
curl localhost:8080/auth/oauth/token -d grant_type=password -d username=$3 -d password=$4 -d scope=write -v --user $1:$2
echo
echo
