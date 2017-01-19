#!/usr/bin/env bash

mvn clean package && ./target/appassembler/bin/run server config.yaml