@echo off

rem JDKをインストールして、JDKのパスを記入
rem すでに環境変数で設定している場合には、不要
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_101
set ANT_HOME=C:\dcha-devenv2\eclipse\plugins\org.apache.ant_1.9.6.v201510161327
set ANDROID_HOME=C:\dcha-devenv2\android-sdk
set PATH=C:\dcha-devenv2\android-sdk\tools;C:\dcha-devenv2\android-sdk\platform-tools;%PATH%
set ANT_EXE=C:\dcha-devenv2\eclipse\plugins\org.apache.ant_1.9.6.v201510161327\bin\ant

rem プロジェクト名を記入
set MAINPRJ=TouchSetupShoZemi

rem プロジェクトのワークスペースを記入
rem set MAINWS=C:\dcha-devenv2\workspace
set MAINWS=C:\Users\otuboyas\Documents\src\TouchSetupShoZemi

rem テストプロジェクト名を記入
set TESTPRJ=TouchSetupShoZemiTest2

rem テストプロジェクトのワークスペースがMAINWSと違う場合は記入
set TESTWS=C:\dcha-devenv2\workspace

if not exist "%JAVA_HOME%\bin\javac.exe" (
	echo.
	echo "JDKが見つかりません。"
	echo.
	pause
rem	exit
)

if "%MAINPRJ%" == "" (
	echo.
	echo "プロジェクト名が未記入です。"
	echo.
	pause
rem	exit
)

if "%TESTPRJ%" == "" (
	echo.
	echo "テストプロジェクト名が未記入です。"
	echo.
	pause
rem	exit
)

if "%TESTWS%" == "" (
	set TESTWS=%MAINWS%
)

call android -v update project -p %MAINWS%\%MAINPRJ% -t 1 -s

call android -v update test-project -m %MAINWS%\%MAINPRJ% -p %TESTWS%\%TESTPRJ%

cd %MAINWS%\%MAINPRJ%
call %ANT_EXE% emma debug install

cd %TESTWS%\%TESTPRJ%
call %ANT_EXE% emma debug install test

pause
