#!/bin/bash
JAVAPATH="/home/idiot/Documents/playground/java/ProRewrite/"
rm -r /home/idiot/Documents/playground/java/ProRewrite/classes/Abgabe1/*.class
cd $JAVAPATH 
javac -d $JAVAPATH/classes Abgabe1/Runner.java
cd classes
java Abgabe1.Runner
