@echo off
del /s /q out\*
if not exist out mkdir out
javac -d out game/*.java
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%
java -cp out game.Main
Timeout -1
