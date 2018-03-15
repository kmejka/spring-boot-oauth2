#!/usr/bin/env bash
echo "##### SINGLE CUSTOMER #####"
curl localhost:8090/api/customer/v1/$1 -v
echo
echo