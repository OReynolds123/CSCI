#!/bin/bash

set -ex
clear
rm -rf bin/*
javac -d bin -cp listadt.jar src/cs1302/list/AbstractStringList.java
javac -d bin -cp listadt.jar:bin src/cs1302/list/ArrayStringList.java
javac -d bin -cp listadt.jar:bin src/cs1302/list/LinkedStringList.java
javac -d bin -cp listadt.jar:bin src/cs1302/list/Driver.java
#javadoc -d doc -sourcepath src -subpackages cs1302 -link https://docs.oracle.com/javase/8/docs/api -link http://csweb.cs.uga.edu/~mec/cs1302/listadt-api -cp listadt.jar
clear
java -cp listadt.jar:bin cs1302.list.Driver
