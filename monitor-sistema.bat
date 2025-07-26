@echo off
setlocal enabledelayedexpansion
title Monitor Sistema Clínico Integrador
color 0A

:inicio
cls
echo ================================================
echo        MONITOR SISTEMA CLINICO INTEGRADOR
echo ================================================
echo Fecha: %date% %time%
echo Usuario: %username%
echo Equipo: %computername%
echo.

::Verificar si Java está instalado
java -version >nul 2>&1
if %ERRORLEVEL%==0 (
    echo [+] Java: Instalado y funcionando
) else (
    echo [-] Java: No encontrado o no configurado
)

:: Verificar si la aplicacion está corriendo
tasklist /FI "IMAGENAME eq java.exe" /V | find /i "ClinicaIntegrador" >nul 2>&1
if %ERRORLEVEL%==0 (
    echo [+] Aplicacion: EJECUTANDO
) else (
    :: Método alternativo: buscar por ventana del título
    tasklist /FI "IMAGENAME eq java.exe" /V | find /i "Sistema Clínico" >nul 2>&1
    if !ERRORLEVEL!==0 (
        echo [+] Aplicacion: EJECUTANDO ^(Login^)
    ) else (
        :: Método básico: verificar si hay algún java.exe corriendo
        tasklist /FI "IMAGENAME eq java.exe" >nul 2>&1
        if !ERRORLEVEL!==0 (
            echo [!] Aplicacion: Java detectado ^(verificar manualmente^)
        ) else (
            echo [-] Aplicacion: DETENIDA
        )
    )
)

:: Verificar servicios MySQL (XAMPP/MySQL oficial)
echo.
echo [*] Verificando servicios MySQL...

:: Primero verificar si mysqld.exe está corriendo (XAMPP)
tasklist /FI "IMAGENAME eq mysqld.exe" >nul 2>&1
if %ERRORLEVEL%==0 (
    echo [+] MySQL: ACTIVO ^(XAMPP^)
    goto mysql_found
)

:: Verificar servicios MySQL estándar
net start | find /i "mysql" >nul 2>&1
if %ERRORLEVEL%==0 (
    echo [+] MySQL: SERVICIO DETECTADO
    net start | find /i "mysql"
    goto mysql_found
)

:: Verificar servicios específicos de XAMPP
sc query mysql80 >nul 2>&1 || sc query mysql >nul 2>&1
if %ERRORLEVEL%==0 (
    for /f "tokens=3 delims=: " %%a in ('sc query mysql80 ^| find "STATE" 2^>nul') do (
        if "%%a"=="RUNNING" (
            echo [+] MySQL: ACTIVO
            goto mysql_found
        ) else (
            echo [!] MySQL: INACTIVO
            goto mysql_found
        )
    )
    for /f "tokens=3 delims=: " %%a in ('sc query mysql ^| find "STATE" 2^>nul') do (
        if "%%a"=="RUNNING" (
            echo [+] MySQL: ACTIVO
            goto mysql_found
        ) else (
            echo [!] MySQL: INACTIVO
            goto mysql_found
        )
    )
)

:: Verificar puerto MySQL (alternativo)
netstat -an | find ":3306" >nul 2>&1
if %ERRORLEVEL%==0 (
    echo [+] MySQL: PUERTO 3306 ACTIVO
    goto mysql_found
)

echo [-] MySQL: NO DETECTADO
echo     Verifique que XAMPP esté iniciado

:mysql_found

:: Verificar archivos críticos
echo.
echo [*] Verificando archivos criticos...
if exist "target\ClinicaIntegrador-1.0.0.jar" (
    echo [+] JAR ejecutable: EXISTE
    for %%a in ("target\ClinicaIntegrador-1.0.0.jar") do (
        set jar_size=%%~za
        echo     Tamano JAR: !jar_size! bytes
    )
) else (
    echo [-] JAR ejecutable: NO EXISTE
)

if exist "src\main\resources\application.properties" (
    echo [+] Configuracion: EXISTE
) else (
    echo [-] Configuracion: NO EXISTE
)

:: Verificar directorio de logs
if exist "logs\" (
    echo [+] Directorio logs: Disponible
    
    :: Contar archivos de log
    for /f %%i in ('dir logs\*.log /b 2^>nul ^| find /c /v ""') do set log_count=%%i
    echo     Archivos log: !log_count!
    
    :: Verificar log principal
    if exist "logs\clinica.log" (
        for %%a in ("logs\clinica.log") do (
            set log_size=%%~za
            echo     Log principal: !log_size! bytes
        )
    )
    
    :: Verificar errores recientes
    if exist "logs\audit.log" (
        for %%a in ("logs\audit.log") do (
            set audit_size=%%~za
            echo     Log auditoria: !audit_size! bytes
        )
    )
) else (
    echo [-] Directorio logs: No encontrado
)

:: Verificar espacio en disco
echo.
echo [*] Verificando espacio en disco...
for /f "tokens=3" %%a in ('dir C:\ ^| find "bytes free"') do (
    set free_space=%%a
    echo     Espacio libre: !free_space! bytes
)

:: Verificar conexión localhost
ping -n 1 localhost >nul
if %ERRORLEVEL%==0 (
    echo [+] Localhost: Accesible
) else (
    echo [-] Localhost: No accesible
)

echo.
echo ================================================
echo                    OPCIONES
echo ================================================
echo [1] Ver logs recientes
echo [2] Iniciar aplicacion ^(desarrollo^)
echo [3] Ejecutar despliegue completo
echo [4] Verificar base de datos
echo [5] Iniciar aplicacion ^(usuario final^)
echo [6] Backup base de datos
echo [7] Configurar MySQL
echo [8] Salir
echo [R] Refrescar ^(automático en 30s^)
echo ================================================

:: Auto-refresh después de 30 segundos
choice /c 12345678R /t 30 /d R /m "Seleccione opción:"

if %ERRORLEVEL%==1 goto ver_logs
if %ERRORLEVEL%==2 goto iniciar_app
if %ERRORLEVEL%==3 goto ejecutar_despliegue
if %ERRORLEVEL%==4 goto verificar_db
if %ERRORLEVEL%==5 goto iniciar_app_silencioso
if %ERRORLEVEL%==6 goto backup_bd
if %ERRORLEVEL%==7 goto configurar_mysql
if %ERRORLEVEL%==8 goto salir
if %ERRORLEVEL%==9 goto inicio

:ver_logs
cls
echo ================================================
echo                LOGS RECIENTES
echo ================================================
if exist "logs\clinica.log" (
    echo Últimas 15 líneas del log principal:
    echo.
    powershell -command "Get-Content logs\clinica.log -Tail 15"
) else (
    echo No se encontró el archivo de log principal.
)

if exist "logs\audit.log" (
    echo.
    echo ----------------------------------------
    echo Últimas 10 líneas del log de auditoría:
    echo.
    powershell -command "Get-Content logs\audit.log -Tail 10"
)

echo.
echo ================================================
pause
goto inicio

:iniciar_app
echo.
echo [*] Iniciando aplicacion...
if exist "target\ClinicaIntegrador-1.0.0.jar" (
    echo Ejecutando JAR en segundo plano...
    start /min java -jar target\ClinicaIntegrador-1.0.0.jar
    echo [+] Aplicacion iniciada exitosamente
) else (
    echo [-] Error: JAR no encontrado
    echo     Ejecute primero el despliegue ^(opcion 3^)
)
echo.
timeout /t 3 >nul
goto inicio

:ejecutar_despliegue
echo.
echo [*] Ejecutando despliegue...
if exist "deploy-production.bat" (
    call deploy-production.bat
    echo.
    echo [+] Despliegue completado
) else (
    echo [-] Error: Script de despliegue no encontrado
)
echo.
pause
goto inicio

:verificar_db
echo.
echo [*] Verificando base de datos...
if exist "configurar-mysql.sql" (
    echo Archivo de configuracion encontrado
    echo.
    echo Para configurar la base de datos:
    echo 1. Abrir MySQL Workbench
    echo 2. Ejecutar: configurar-mysql.sql
    echo 3. Verificar que la BD 'citas_2' este creada
) else (
    echo [-] Error: Archivo de configuracion no encontrado
)
echo.
pause
goto inicio

:iniciar_app_silencioso
echo.
echo [*] Iniciando aplicacion ^(modo usuario final^)...
if exist "target\ClinicaIntegrador-1.0.0.jar" (
    echo Ejecutando JAR sin ventana de consola...
    start /min java -jar target\ClinicaIntegrador-1.0.0.jar
    echo [+] Aplicacion iniciada en segundo plano
    echo     No se mostrara ventana de consola al usuario
) else (
    echo [-] Error: JAR no encontrado
    echo     Ejecute primero el despliegue ^(opcion 3^)
)
echo.
timeout /t 3 >nul
goto inicio

:backup_bd
echo.
echo [*] Realizando backup de base de datos...
if exist "backup-bd.bat" (
    echo Ejecutando script de backup...
    call backup-bd.bat
    echo.
    echo [+] Proceso de backup completado
) else (
    echo [-] Error: Script de backup no encontrado
    echo     Archivo requerido: backup-bd.bat
)
echo.
pause
goto inicio

:configurar_mysql
cls
echo ================================================
echo              CONFIGURAR MYSQL
echo ================================================
echo.
echo [*] Ejecutando configurador de MySQL...
echo.
if exist "configurar-mysql.bat" (
    call configurar-mysql.bat
    echo.
    echo [+] Configuracion de MySQL completada
) else (
    echo [-] Error: Script de configuracion no encontrado
    echo     Archivo requerido: configurar-mysql.bat
)
echo.
pause
goto inicio

:salir
echo.
echo [*] Cerrando monitor del sistema...
echo Gracias por usar el Sistema Clinico Integrador
timeout /t 2 >nul
exit
