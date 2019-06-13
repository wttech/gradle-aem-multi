powershell -command "Start-Process cmd -ArgumentList '/C cd %CD% && gradlew.bat environmentHosts --no-daemon' -Verb runas"
