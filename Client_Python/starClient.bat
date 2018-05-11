@echo off
omniidl -bpython ../Hangman.idl
python Client.py -ORBbootstrapAgentPort 6000 -ORBbootstrapAgentHostname localhost