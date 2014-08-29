for /f "tokens=5*" %%A in ('REG QUERY "HKLM\Software\Microsoft\Windows\CurrentVersion\Uninstall\Sanny Builder 3_is1" /v "Inno Setup: App Path"') DO SET SannyDir=%%B\

:: Uncomment and set the right folder below if the folder cannot be found automatically.
::SET SannyDir="C:\Program Files\Sanny Builder 3\"


START "title" /D "%SannyDir%" /WAIT "sanny.exe" \nosplash \gta3 \compile "%CD%\main.sc" "%CD%\Compiled SCM\main.scm"

START "title" /D "%SannyDir%" /WAIT "sanny.exe" \nosplash \gta3 \compile "%CD%\main_debug.sc" "%CD%\Compiled SCM\main_debug.scm"
