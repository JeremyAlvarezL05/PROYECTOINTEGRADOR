@echo off
setlocal enabledelayedexpansion
title Backup Base de Datos - Sistema Clinico
color 0B

echo ========================================
echo      BACKUP BASE DE DATOS
echo         Sistema Clinico Integrador
echo ========================================
echo Fecha: %date% %time%
echo.

REM Configuración
set DB_NAME=citas_2
set DB_USER=root
set DB_PASSWORD=
set BACKUP_DIR=backups

REM Crear timestamp más simple
for /f "tokens=2 delims==" %%a in ('wmic OS Get localdatetime /value') do set "dt=%%a"
set "YY=%dt:~2,2%" & set "YYYY=%dt:~0,4%" & set "MM=%dt:~4,2%" & set "DD=%dt:~6,2%"
set "HH=%dt:~8,2%" & set "Min=%dt:~10,2%" & set "Sec=%dt:~12,2%"
set "timestamp=%YYYY%-%MM%-%DD%_%HH%-%Min%-%Sec%"

set BACKUP_FILE=backup_!DB_NAME!_!timestamp!.sql

echo 🗄️ Configuración del backup:
echo    📋 Base de datos: %DB_NAME%
echo    👤 Usuario: %DB_USER%
echo    📁 Directorio: %BACKUP_DIR%
echo    📄 Archivo: !BACKUP_FILE!
echo.

REM Crear directorio de backups si no existe
if not exist "%BACKUP_DIR%" (
    echo 📁 Creando directorio de backups...
    mkdir "%BACKUP_DIR%"
    echo    ✅ Directorio creado: %BACKUP_DIR%
) else (
    echo    ✅ Directorio existe: %BACKUP_DIR%
)

echo.
echo 🔄 Iniciando backup...
echo.

REM Verificar si MySQL está disponible
mysql --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ MySQL no está instalado o no está en PATH
    echo.
    echo 🔧 Soluciones posibles:
    echo    1. Instalar MySQL desde: https://dev.mysql.com/downloads/installer/
    echo    2. Agregar MySQL al PATH del sistema
    echo    3. Usar XAMPP/WAMPP que incluye MySQL
    echo    4. Ejecutar desde la carpeta de MySQL
    echo.
    echo 📍 Ubicaciones comunes de MySQL:
    echo    - C:\Program Files\MySQL\MySQL Server 8.0\bin\
    echo    - C:\xampp\mysql\bin\
    echo    - C:\wamp\bin\mysql\mysql8.0.x\bin\
    echo.
    goto mysql_error
)

REM Ejecutar mysqldump
echo 🔄 Ejecutando mysqldump...
mysqldump -u %DB_USER% --password=%DB_PASSWORD% --routines --triggers --single-transaction %DB_NAME% > "%BACKUP_DIR%\%BACKUP_FILE%" 2>"%BACKUP_DIR%\error.log"

REM Verificar si el backup fue exitoso
if %errorlevel% equ 0 (
    REM Verificar que el archivo no esté vacío
    for %%a in ("%BACKUP_DIR%\%BACKUP_FILE%") do set backup_size=%%~za
    if !backup_size! gtr 0 (
        echo ✅ Backup completado exitosamente
        echo.
        
        REM Mostrar información del archivo generado
        if exist "%BACKUP_DIR%\%BACKUP_FILE%" (
            for %%a in ("%BACKUP_DIR%\%BACKUP_FILE%") do (
                echo 📊 Información del backup:
                echo    📄 Archivo: %%~nxa
                echo    📏 Tamaño: %%~za bytes
                echo    🕒 Creado: %%~ta
            )
        )
        
        echo.
        echo 📋 Archivo guardado en: %BACKUP_DIR%\%BACKUP_FILE%
    ) else (
        echo ❌ El backup está vacío
        echo.
        echo 💡 Posibles causas:
        echo    - La base de datos '%DB_NAME%' no existe
        echo    - No hay datos en la base de datos
        echo    - Credenciales incorrectas
        echo    - Permisos insuficientes
        echo.
        if exist "%BACKUP_DIR%\error.log" (
            echo 🔍 Detalles del error:
            type "%BACKUP_DIR%\error.log"
        )
        goto backup_error
    )
) else (
    echo ❌ Error al crear el backup
    echo.
    if exist "%BACKUP_DIR%\error.log" (
        echo 🔍 Detalles del error:
        type "%BACKUP_DIR%\error.log"
        echo.
    )
    echo 💡 Posibles causas:
    echo    - Credenciales incorrectas
    echo    - Base de datos no existe
    echo    - Permisos insuficientes
    echo    - MySQL no está funcionando
    echo.
    goto backup_error
)

echo.
echo 📈 Gestión de backups:
if exist "%BACKUP_DIR%\backup_*.sql" (
    echo.
    echo 📁 Backups existentes:
    dir /b "%BACKUP_DIR%\backup_*.sql" | findstr /N "."
    
    REM Contar archivos de backup
    for /f %%i in ('dir /b "%BACKUP_DIR%\backup_*.sql" 2^>nul ^| find /c /v ""') do set backup_count=%%i
    echo.
    echo 📊 Total de backups: !backup_count!
    
    REM Sugerir limpieza si hay muchos backups
    if !backup_count! GTR 10 (
        echo.
        echo ⚠️  Tienes más de 10 backups
        echo 💡 Considera ejecutar: limpiar-backups.bat
    )
) else (
    echo    📂 No hay backups previos
)

echo.
echo ========================================
echo Opciones:
echo [1] Crear otro backup
echo [2] Ver backups existentes
echo [3] Limpiar backups antiguos
echo [4] Salir
echo ========================================

choice /c 1234 /m "Seleccione opción:"

if %ERRORLEVEL%==1 goto inicio
if %ERRORLEVEL%==2 goto ver_backups
if %ERRORLEVEL%==3 goto limpiar_backups
if %ERRORLEVEL%==4 goto salir

:inicio
cls
goto :eof

:ver_backups
cls
echo ========================================
echo         BACKUPS EXISTENTES
echo ========================================
echo.
if exist "%BACKUP_DIR%\backup_*.sql" (
    echo 📁 Archivos de backup encontrados:
    echo.
    for %%f in ("%BACKUP_DIR%\backup_*.sql") do (
        echo 📄 %%~nxf
        echo    📏 Tamaño: %%~zf bytes
        echo    🕒 Fecha: %%~tf
        echo.
    )
) else (
    echo 📂 No hay backups disponibles
)
echo.
pause
goto inicio

:limpiar_backups
cls
echo ========================================
echo      LIMPIAR BACKUPS ANTIGUOS
echo ========================================
echo.
echo ⚠️  Esta acción eliminará backups antiguos
echo 💡 Se mantendrán los 5 más recientes
echo.
set /p confirm=¿Continuar? (S/N): 
if /i "%confirm%"=="S" (
    echo.
    echo 🧹 Limpiando backups antiguos...
    
    REM Contar archivos antes
    for /f %%i in ('dir /b "%BACKUP_DIR%\backup_*.sql" 2^>nul ^| find /c /v ""') do set before_count=%%i
    
    REM Eliminar archivos antiguos (mantener solo los 5 más recientes)
    for /f "skip=5 delims=" %%f in ('dir /b /o:-d "%BACKUP_DIR%\backup_*.sql" 2^>nul') do (
        del "%BACKUP_DIR%\%%f"
        echo    🗑️ Eliminado: %%f
    )
    
    REM Contar archivos después
    for /f %%i in ('dir /b "%BACKUP_DIR%\backup_*.sql" 2^>nul ^| find /c /v ""') do set after_count=%%i
    
    echo.
    echo ✅ Limpieza completada
    echo    📊 Antes: !before_count! archivos
    echo    📊 Después: !after_count! archivos
) else (
    echo ❌ Operación cancelada
)
echo.
pause
goto inicio

:salir
echo.
echo 💾 Backup completado
echo 📁 Revisa la carpeta: %BACKUP_DIR%
echo 👋 Gracias por usar el sistema de backup
echo.
timeout /t 3 >nul
exit

:mysql_error
echo.
echo 🔧 Instrucciones para instalar MySQL:
echo.
echo 1. Opción fácil - XAMPP:
echo    - Descarga XAMPP desde: https://www.apachefriends.org/
echo    - Instala e inicia Apache y MySQL
echo    - Agrega C:\xampp\mysql\bin al PATH
echo.
echo 2. Opción oficial - MySQL:
echo    - Descarga MySQL Installer desde: https://dev.mysql.com/downloads/installer/
echo    - Instala MySQL Server
echo    - Agrega la carpeta bin al PATH del sistema
echo.
echo 3. Verificar instalación:
echo    - Abre nueva ventana de comandos
echo    - Ejecuta: mysql --version
echo.
pause
goto salir

:backup_error
echo.
echo 🔧 Para solucionar el backup vacío:
echo    1. Verificar que MySQL esté funcionando
echo    2. Verificar que la base de datos 'citas_2' exista
echo    3. Verificar credenciales de usuario
echo    4. Verificar que haya datos en la base de datos
echo.
echo 💡 Comandos útiles:
echo    mysql -u root -p
echo    SHOW DATABASES;
echo    USE citas_2;
echo    SHOW TABLES;
echo.
pause
goto salir
