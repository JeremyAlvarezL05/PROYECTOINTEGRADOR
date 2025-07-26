@echo off
title Limpiar Backups - Sistema Clinico
color 0C

echo ========================================
echo         LIMPIAR BACKUPS ANTIGUOS
echo ========================================
echo.

set BACKUP_DIR=backups

if not exist "%BACKUP_DIR%" (
    echo ❌ Directorio de backups no existe: %BACKUP_DIR%
    echo.
    pause
    exit
)

echo 📁 Directorio: %BACKUP_DIR%
echo.

REM Contar archivos de backup
for /f %%i in ('dir /b "%BACKUP_DIR%\backup_*.sql" 2^>nul ^| find /c /v ""') do set total_backups=%%i

if %total_backups% EQU 0 (
    echo 📂 No hay archivos de backup para limpiar
    echo.
    pause
    exit
)

echo 📊 Total de backups encontrados: %total_backups%
echo.

if %total_backups% LEQ 5 (
    echo ✅ Tienes %total_backups% backups (cantidad recomendada)
    echo 💡 No es necesario limpiar
    echo.
    pause
    exit
)

echo 📋 Archivos que se mantendrán (5 más recientes):
echo.
for /f "tokens=1-5 delims=" %%f in ('dir /b /o:-d "%BACKUP_DIR%\backup_*.sql" 2^>nul') do (
    echo    ✅ %%f
)

echo.
echo 🗑️ Archivos que se eliminarán:
echo.
for /f "skip=5 delims=" %%f in ('dir /b /o:-d "%BACKUP_DIR%\backup_*.sql" 2^>nul') do (
    echo    ❌ %%f
)

echo.
set /p confirm=¿Eliminar archivos antiguos? (S/N): 
if /i "%confirm%" NEQ "S" (
    echo ❌ Operación cancelada
    echo.
    pause
    exit
)

echo.
echo 🧹 Eliminando archivos antiguos...
set deleted_count=0

for /f "skip=5 delims=" %%f in ('dir /b /o:-d "%BACKUP_DIR%\backup_*.sql" 2^>nul') do (
    del "%BACKUP_DIR%\%%f"
    if exist "%BACKUP_DIR%\%%f" (
        echo    ❌ Error al eliminar: %%f
    ) else (
        echo    ✅ Eliminado: %%f
        set /a deleted_count+=1
    )
)

echo.
echo ========================================
echo         LIMPIEZA COMPLETADA
echo ========================================
echo.
echo 📊 Archivos eliminados: %deleted_count%
echo 📊 Archivos mantenidos: 5
echo 📁 Directorio: %BACKUP_DIR%
echo.
echo ✅ Limpieza exitosa
echo.
pause
