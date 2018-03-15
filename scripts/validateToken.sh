#!/usr/bin/env bash
curl localhost:8080/auth/oauth/check_token -d token=$3 -u $1:$2 -v