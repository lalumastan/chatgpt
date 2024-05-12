@ECHO OFF
CALL gvm
CALL mvn -Pnative clean native:compile