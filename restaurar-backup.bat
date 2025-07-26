@echo off
setlocal enabledelayedexpansion
title Restaurar Backup - Sistema Clinico
color 0E

echo ========================================
echo         RESTAURAR BACKUP
echo         Sistema Clinico Integrador
echo ========================================
echo.

REM Configuración
set DB_NAME=citas_2
set DB_USER=root
set DB_PASSWORD=
set BACKUP_DIR=backups

echo 🗄️ Configuración de restauración:
echo    📋 Base de datos: %DB_NAME%
echo    👤 Usuario: %DB_USER%
echo    📁 Directorio: %BACKUP_DIR%
echo.

REM Verificar que existe el directorio de backups
if not exist "%BACKUP_DIR%" (
    echo ❌ Directorio de backups no existe: %BACKUP_DIR%
    echo 💡 Primero crea un backup con: backup-bd.bat
    echo.
    pause
    exit
)

REM Mostrar archivos de backup disponibles
echo 📂 Backups disponibles:
echo.
set backup_count=0
for %%f in ("%BACKUP_DIR%\backup_*.sql") do (
    set /a backup_count+=1
    echo [!backup_count!] %%~nxf
    echo     📏 Tamaño: %%~zf bytes
    echo     🕒 Fecha: %%~tf
    echo.
)

if %backup_count% EQU 0 (
    echo 📂 No hay backups disponibles
    echo 💡 Primero crea un backup con: backup-bd.bat
    echo.
    pause
    exit
)

echo ========================================
echo Seleccione el backup a restaurar:
echo [0] Cancelar
echo ========================================

set /p selection=Ingrese el número del backup: 

if "%selection%"=="0" (
    echo ❌ Operación cancelada
    echo.
    pause
    exit
)

REM Validar selección
if %selection% LSS 1 goto invalid_selection
if %selection% GTR %backup_count% goto invalid_selection

REM Obtener el archivo seleccionado
set current_count=0
for %%f in ("%BACKUP_DIR%\backup_*.sql") do (
    set /a current_count+=1
    if !current_count! EQU %selection% (
        set selected_file=%%f
        set selected_name=%%~nxf
    )
)

echo.
echo 📄 Archivo seleccionado: %selected_name%
echo.

REM Advertencia de seguridad
echo ⚠️  ADVERTENCIA IMPORTANTE:
echo    Esta operación ELIMINARÁ todos los datos actuales
echo    y los reemplazará con los datos del backup
echo.
echo 💡 Recomendación: Hacer un backup actual antes de restaurar
echo.

set /p confirm=¿Está seguro de continuar? (S/N): 
if /i "%confirm%" NEQ "S" (
    echo ❌ Operación cancelada
    echo.
    pause
    exit
)

echo.
echo 🔄 Restaurando backup...
echo    📄 Archivo: %selected_name%
echo    📋 Base de datos: %DB_NAME%
echo.

REM Ejecutar mysql para restaurar
mysql -u %DB_USER% --password=%DB_PASSWORD% %DB_NAME% < "%selected_file%" 2>nul

if %errorlevel% equ 0 (
    echo ✅ Restauración completada exitosamente
    echo.
    echo 📊 Información de la restauración:
    echo    📄 Archivo: %selected_name%
    echo    📋 Base de datos: %DB_NAME%
    echo    🕒 Restaurado: %date% %time%
    echo.
    echo 💡 La base de datos ha sido restaurada al estado
    echo    del backup seleccionado
    
) else (
    echo ❌ Error al restaurar el backup
    echo.
    echo 💡 Posibles causas:
    echo    - MySQL no está instalado o no está en PATH
    echo    - Credenciales incorrectas
    echo    - Base de datos no existe
    echo    - Archivo de backup corrupto
    echo    - Permisos insuficientes
    echo.
    echo 🔧 Para solucionar:
    echo    1. Verificar que MySQL esté instalado
    echo    2. Verificar que la base de datos '%DB_NAME%' exista
    echo    3. Verificar credenciales de usuario
    echo    4. Verificar integridad del archivo de backup
)

echo.
echo ========================================
echo         RESTAURACIÓN COMPLETADA
echo ========================================
echo.
pause
exit

:invalid_selection
echo ❌ Selección inválida
echo 💡 Ingrese un número entre 1 y %backup_count%
echo.
pause
exit
