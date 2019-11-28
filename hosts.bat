echo gradlew.bat environmentHosts -q > .gap.hosts.tmp

powershell -command "Start-Process cmd -ArgumentList '/C cd %CD% && type .gap.hosts.windows >> C:\Windows\System32\drivers\etc\hosts' -Verb runas"

del .gap.hosts.tmp
