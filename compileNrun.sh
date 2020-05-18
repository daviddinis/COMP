#!/bin/bash

echo "Compiling..."
java -jar comp2020-4d.jar test/fixtures/public/$1.jmm
java -jar jasmin.jar jasminFiles/$1.j
echo "Running..."
java $1