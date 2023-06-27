Dim WinScriptHost
Set WinScriptHost = CreateObject("WScript.Shell")
WinScriptHost.Run Chr(34) & "c:\Users\nisar.abbasi\Desktop\chatester.bat" & Chr(34), 0
Set WinScriptHost = Nothing