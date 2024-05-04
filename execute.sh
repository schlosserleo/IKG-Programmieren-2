#!/bin/bash
JAVAPATH="/home/leo/Documents/IKG/2/PRO/Abgabe1/"
rm -r /home/leo/Documents/IKG/2/PRO/Abgabe1/classes/crm/*.class
cd $JAVAPATH/src 
javac -d $JAVAPATH/classes crm/Runner.java
cd ../classes
java crm.Runner
