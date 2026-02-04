@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
REM Logic: Search for all .java files in the src directory to ensure sub-classes are found
dir /s /b ..\src\main\java\*.java > sources.txt
javac -cp ..\src\main\java -Xlint:none -d ..\bin @sources.txt

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    del sources.txt
    exit /b 1
)
del sources.txt

REM run the program, feed commands from input.txt and redirect output
REM Standard: Ensure the classpath points to the bin folder where Skywalker.class is
java -classpath ..\bin Skywalker < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
