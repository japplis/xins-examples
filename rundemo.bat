@echo off
if "%ANT_HOME%"=="" goto noant
if "%JAVA_HOME%"=="" goto nojava

cd xins-project
set XINS_HOME=..\..
echo XINS_HOME directory used is %XINS_HOME%.
echo Compiling and running "myproject" API, use the link provided in the README.html to execute it.
IF EXIST "build\build.xml" del build\build.xml
"%XINS_HOME%\bin\xins.bat" -Dorg.xins.server.config=..\xins.properties specdocs-myproject run-myproject
goto end

noant:
echo Please install ant and set the ANT_HOME variable correctly.
if "%JAVA_HOME%"=="" goto nojava
pause
goto end

nojava:
echo Please install Java and set the JAVA_HOME variable correctly.
pause
goto end

end: