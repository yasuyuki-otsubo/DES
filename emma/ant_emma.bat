@echo off

rem JDK���C���X�g�[�����āAJDK�̃p�X���L��
rem ���łɊ��ϐ��Őݒ肵�Ă���ꍇ�ɂ́A�s�v
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_101
set ANT_HOME=C:\dcha-devenv2\eclipse\plugins\org.apache.ant_1.9.6.v201510161327
set ANDROID_HOME=C:\dcha-devenv2\android-sdk
set PATH=C:\dcha-devenv2\android-sdk\tools;C:\dcha-devenv2\android-sdk\platform-tools;%PATH%
set ANT_EXE=C:\dcha-devenv2\eclipse\plugins\org.apache.ant_1.9.6.v201510161327\bin\ant

rem �v���W�F�N�g�����L��
set MAINPRJ=TouchSetupShoZemi

rem �v���W�F�N�g�̃��[�N�X�y�[�X���L��
rem set MAINWS=C:\dcha-devenv2\workspace
set MAINWS=C:\Users\otuboyas\Documents\src\TouchSetupShoZemi

rem �e�X�g�v���W�F�N�g�����L��
set TESTPRJ=TouchSetupShoZemiTest2

rem �e�X�g�v���W�F�N�g�̃��[�N�X�y�[�X��MAINWS�ƈႤ�ꍇ�͋L��
set TESTWS=C:\dcha-devenv2\workspace

if not exist "%JAVA_HOME%\bin\javac.exe" (
	echo.
	echo "JDK��������܂���B"
	echo.
	pause
rem	exit
)

if "%MAINPRJ%" == "" (
	echo.
	echo "�v���W�F�N�g�������L���ł��B"
	echo.
	pause
rem	exit
)

if "%TESTPRJ%" == "" (
	echo.
	echo "�e�X�g�v���W�F�N�g�������L���ł��B"
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
