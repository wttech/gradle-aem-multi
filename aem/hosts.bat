powershell -command "Start-Process cmd -ArgumentList '/k cd %CD% && gradlew.bat aemEnvHosts --no-daemon' -Verb runas"
