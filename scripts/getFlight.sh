#!/usr/bin/env bash
echo "##### SINGLE FLIGHT #####"
curl localhost:9010/api/flights/v1/$1 -v
echo
echo