#!/usr/bin/env bash
echo "##### ALL FLIGHTS #####"
curl localhost:9010/api/flights/v1 -v
echo
echo