#!/usr/bin/env bash
echo "##### SINGLE BOOKING #####"
curl localhost:9000/api/bookings/v1/$1 -v
echo
echo