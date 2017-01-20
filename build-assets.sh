#!/usr/bin/env bash

rootpath=`pwd`
cd src/main/scripts/node

npm i --loglevel info
npm run build

cd $rootpath
