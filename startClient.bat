@echo off
idlj -fclient ../Hangman.idl
javac *.java
java -cp . Client -ORBInitialPort 7000
