#!/usr/bin/env bash

rootpath=`pwd`
cd src/main/scripts/node

npm i
npm run build

cd $rootpath