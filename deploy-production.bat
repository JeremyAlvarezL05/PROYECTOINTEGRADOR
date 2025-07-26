@echo off
echo ========================================
echo      DESPLIEGUE CLINICA
echo ========================================

REM Compilar aplicación
echo [1/3] Compilando...
call mvn clean package -DskipTests

REM Crear directorio
echo [2/3] Creando directorio...
if not exist "C:\ClinicaApp" mkdir "C:\ClinicaApp"

REM Copiar archivos
echo [3/3] Copiando archivos...
copy "target\ClinicaIntegrador-1.0.0.jar" "C:\ClinicaApp\"
copy "configurar-mysql.sql" "C:\ClinicaApp\"

REM Crear script de inicio profesional
echo @echo off > "C:\ClinicaApp\iniciar.bat"
echo title Sistema Clinico Integrador >> "C:\ClinicaApp\iniciar.bat"
echo echo Iniciando Sistema Clinico... >> "C:\ClinicaApp\iniciar.bat"
echo echo Por favor espere... >> "C:\ClinicaApp\iniciar.bat"
echo echo. >> "C:\ClinicaApp\iniciar.bat"
echo java -jar ClinicaIntegrador-1.0.0.jar >> "C:\ClinicaApp\iniciar.bat"
echo if errorlevel 1 ( >> "C:\ClinicaApp\iniciar.bat"
echo     echo Error al iniciar la aplicacion >> "C:\ClinicaApp\iniciar.bat"
echo     pause >> "C:\ClinicaApp\iniciar.bat"
echo ) >> "C:\ClinicaApp\iniciar.bat"

REM Crear script de inicio silencioso para usuarios finales
echo @echo off > "C:\ClinicaApp\iniciar-silencioso.bat"
echo start /min java -jar ClinicaIntegrador-1.0.0.jar >> "C:\ClinicaApp\iniciar-silencioso.bat"

echo.
echo ========================================
echo         LISTO PARA USAR
echo ========================================
echo.
echo 1. Configurar MySQL: C:\ClinicaApp\configurar-mysql.sql
echo 2. Para desarrollo: C:\ClinicaApp\iniciar.bat
echo 3. Para usuario final: C:\ClinicaApp\iniciar-silencioso.bat
echo.
echo NOTA: El script silencioso no muestra ventana de consola
echo.
pause
