#!/bin/bash
JAVAPATH="/home/idiot/Documents/IKG/PRO/Abgaben/Abgabe1/"
rm -r /home/idiot/Documents/IKG/PRO/Abgaben/Abgabe1/classes/crm/*.class
cd $JAVAPATH/src 
javac -d $JAVAPATH/classes crm/Runner.java
cd ../classes
java crm.Runner
