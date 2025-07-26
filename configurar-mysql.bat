@echo off
setlocal enabledelayedexpansion
title Configurar MySQL - Sistema Clinico
color 0E

echo ========================================
echo       CONFIGURAR MYSQL
echo         Sistema Clinico Integrador
echo ========================================
echo.

echo 🔍 Verificando instalación de MySQL...
echo.

REM Verificar MySQL en PATH
mysql --version >nul 2>&1
if %errorlevel% equ 0 (
    echo ✅ MySQL está instalado y disponible en PATH
    mysql --version
    echo.
    goto check_database
) else (
    echo ❌ MySQL no está en PATH
    echo.
)

echo 🔍 Buscando MySQL en ubicaciones comunes...
echo.

REM Buscar MySQL en ubicaciones comunes
set mysql_found=false

REM XAMPP
if exist "C:\xampp\mysql\bin\mysql.exe" (
    echo ✅ MySQL encontrado en XAMPP: C:\xampp\mysql\bin\
    set mysql_path=C:\xampp\mysql\bin
    set mysql_found=true
    goto found_mysql
)

REM MySQL oficial
for /d %%d in ("C:\Program Files\MySQL\MySQL Server*") do (
    if exist "%%d\bin\mysql.exe" (
        echo ✅ MySQL encontrado en: %%d\bin\
        set mysql_path=%%d\bin
        set mysql_found=true
        goto found_mysql
    )
)

REM WAMP
if exist "C:\wamp64\bin\mysql" (
    for /d %%d in ("C:\wamp64\bin\mysql\mysql*") do (
        if exist "%%d\bin\mysql.exe" (
            echo ✅ MySQL encontrado en WAMP: %%d\bin\
            set mysql_path=%%d\bin
            set mysql_found=true
            goto found_mysql
        )
    )
)

if "%mysql_found%"=="false" (
    echo ❌ No se encontró MySQL instalado
    echo.
    goto install_mysql
)

:found_mysql
echo.
echo 🔧 Opciones de configuración:
echo ========================================
echo [1] Agregar MySQL al PATH del sistema
echo [2] Probar conexión con MySQL
echo [3] Crear base de datos 'citas_2'
echo [4] Verificar servicios de MySQL
echo [5] Mostrar instrucciones manuales
echo [6] Salir
echo ========================================

choice /c 123456 /m "Seleccione opción:"

if %ERRORLEVEL%==1 goto add_to_path
if %ERRORLEVEL%==2 goto test_connection
if %ERRORLEVEL%==3 goto create_database
if %ERRORLEVEL%==4 goto check_services
if %ERRORLEVEL%==5 goto manual_instructions
if %ERRORLEVEL%==6 goto exit

:add_to_path
cls
echo ========================================
echo      AGREGAR MYSQL AL PATH
echo ========================================
echo.
echo 📍 Ruta de MySQL encontrada: !mysql_path!
echo.
echo ⚠️  Esta acción requiere permisos de administrador
echo.
echo 🔧 Instrucciones manuales:
echo    1. Presiona Win + X y selecciona "Sistema"
echo    2. Haz clic en "Configuración avanzada del sistema"
echo    3. Haz clic en "Variables de entorno"
echo    4. En "Variables del sistema", busca "Path"
echo    5. Haz clic en "Editar" y luego "Nuevo"
echo    6. Agrega: !mysql_path!
echo    7. Haz clic en "Aceptar" en todas las ventanas
echo    8. Reinicia el símbolo del sistema
echo.
echo 💡 Alternativa rápida (temporal):
echo    set PATH=!mysql_path!;%%PATH%%
echo.
pause
goto found_mysql

:test_connection
cls
echo ========================================
echo      PROBAR CONEXIÓN MYSQL
echo ========================================
echo.
echo 🔄 Probando conexión con MySQL...
echo.

REM Primero intentar sin contraseña
echo 🔄 Intentando conexión sin contraseña...
if "%mysql_found%"=="true" (
    echo 💡 Usando MySQL en: !mysql_path!
    "!mysql_path!\mysql.exe" -u root -e "SELECT 'Conexión exitosa SIN contraseña' AS resultado;" 2>nul
    if !errorlevel! equ 0 (
        echo ✅ Conexión exitosa sin contraseña
        set mysql_no_password=true
    ) else (
        echo ⚠️  Conexión sin contraseña falló, probando con contraseña...
        "!mysql_path!\mysql.exe" -u root -p -e "SELECT 'Conexión exitosa CON contraseña' AS resultado;"
        set mysql_no_password=false
    )
) else (
    mysql -u root -e "SELECT 'Conexión exitosa SIN contraseña' AS resultado;" 2>nul
    if !errorlevel! equ 0 (
        echo ✅ Conexión exitosa sin contraseña
        set mysql_no_password=true
    ) else (
        echo ⚠️  Conexión sin contraseña falló, probando con contraseña...
        mysql -u root -p -e "SELECT 'Conexión exitosa CON contraseña' AS resultado;"
        set mysql_no_password=false
    )
)

echo.
pause
goto found_mysql

:create_database
cls
echo ========================================
echo      CREAR BASE DE DATOS
echo ========================================
echo.
echo 🔄 Creando base de datos 'citas_2'...
echo.

REM Primero intentar sin contraseña
echo 🔄 Intentando crear base de datos sin contraseña...
if "%mysql_found%"=="true" (
    echo 💡 Usando MySQL en: !mysql_path!
    "!mysql_path!\mysql.exe" -u root -e "CREATE DATABASE IF NOT EXISTS citas_2; SHOW DATABASES;" 2>nul
    if !errorlevel! equ 0 (
        echo ✅ Base de datos creada exitosamente (sin contraseña)
        set mysql_no_password=true
    ) else (
        echo ⚠️  Creación sin contraseña falló, probando con contraseña...
        "!mysql_path!\mysql.exe" -u root -p -e "CREATE DATABASE IF NOT EXISTS citas_2; SHOW DATABASES;"
        set mysql_no_password=false
    )
) else (
    mysql -u root -e "CREATE DATABASE IF NOT EXISTS citas_2; SHOW DATABASES;" 2>nul
    if !errorlevel! equ 0 (
        echo ✅ Base de datos creada exitosamente (sin contraseña)
        set mysql_no_password=true
    ) else (
        echo ⚠️  Creación sin contraseña falló, probando con contraseña...
        mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS citas_2; SHOW DATABASES;"
        set mysql_no_password=false
    )
)

echo.
pause
goto found_mysql

:check_services
cls
echo ========================================
echo      VERIFICAR SERVICIOS MYSQL
echo ========================================
echo.
echo 🔍 Verificando servicios de MySQL...
echo.

REM Verificar servicios de MySQL
sc query | findstr /i mysql
if %errorlevel% equ 0 (
    echo.
    echo 📊 Detalles de servicios MySQL:
    for /f "tokens=2" %%s in ('sc query ^| findstr /i mysql') do (
        echo.
        sc query %%s
    )
) else (
    echo ❌ No se encontraron servicios de MySQL ejecutándose
)

echo.
echo 🔄 Verificando procesos MySQL...
tasklist | findstr /i mysql.exe
if %errorlevel% equ 0 (
    echo ✅ Proceso MySQL ejecutándose
) else (
    echo ❌ Proceso MySQL no encontrado
)

echo.
pause
goto found_mysql

:check_database
cls
echo ========================================
echo      VERIFICAR BASE DE DATOS
echo ========================================
echo.
echo 🔍 Verificando base de datos 'citas_2'...
echo.

REM Primero intentar sin contraseña
echo 🔄 Intentando conexión sin contraseña...
mysql -u root -e "USE citas_2; SHOW TABLES;" 2>nul
if %errorlevel% equ 0 (
    echo ✅ Base de datos 'citas_2' existe y es accesible (sin contraseña)
    echo.
    mysql -u root -e "USE citas_2; SELECT COUNT(*) as total_tablas FROM information_schema.tables WHERE table_schema = 'citas_2';"
    set mysql_no_password=true
) else (
    echo ⚠️  Conexión sin contraseña falló, probando con contraseña...
    echo.
    mysql -u root -p -e "USE citas_2; SHOW TABLES;" 2>nul
    if !errorlevel! equ 0 (
        echo ✅ Base de datos 'citas_2' existe y es accesible (con contraseña)
        echo.
        mysql -u root -p -e "USE citas_2; SELECT COUNT(*) as total_tablas FROM information_schema.tables WHERE table_schema = 'citas_2';"
        set mysql_no_password=false
    ) else (
        echo ❌ Base de datos 'citas_2' no existe o no es accesible
        echo.
        echo 🔧 ¿Deseas crear la base de datos?
        set /p create_db=¿Crear base de datos 'citas_2'? (S/N): 
        if /i "!create_db!"=="S" (
            echo.
            echo 🔄 Intentando crear base de datos sin contraseña...
            mysql -u root -e "CREATE DATABASE IF NOT EXISTS citas_2;" 2>nul
            if !errorlevel! equ 0 (
                echo ✅ Base de datos 'citas_2' creada (sin contraseña)
                set mysql_no_password=true
            ) else (
                echo ⚠️  Creación sin contraseña falló, probando con contraseña...
                mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS citas_2;"
                if !errorlevel! equ 0 (
                    echo ✅ Base de datos 'citas_2' creada (con contraseña)
                    set mysql_no_password=false
                ) else (
                    echo ❌ Error al crear la base de datos
                )
            )
        )
    )
)

echo.
pause
goto found_mysql

:install_mysql
cls
echo ========================================
echo       INSTALAR MYSQL
echo ========================================
echo.
echo ❌ MySQL no está instalado en tu sistema
echo.
echo 🔧 Opciones de instalación:
echo.
echo 1. 🎯 XAMPP (Recomendado para desarrollo):
echo    - Incluye Apache, MySQL, PHP
echo    - Fácil de instalar y usar
echo    - Descarga: https://www.apachefriends.org/
echo.
echo 2. 🏢 MySQL Server (Instalación oficial):
echo    - Solo MySQL Server
echo    - Más configuración manual
echo    - Descarga: https://dev.mysql.com/downloads/installer/
echo.
echo 3. 🌐 MySQL con WAMP:
echo    - Incluye Apache, MySQL, PHP
echo    - Alternativa a XAMPP
echo    - Descarga: https://www.wampserver.com/
echo.
echo 💡 Después de instalar, ejecuta este script nuevamente
echo.
pause
goto exit

:manual_instructions
cls
echo ========================================
echo      INSTRUCCIONES MANUALES
echo ========================================
echo.
echo 📋 Pasos para configurar MySQL manualmente:
echo.
echo 1. 🔧 Verificar instalación:
echo    - Abre cmd como administrador
echo    - Ejecuta: mysql --version
echo.
echo 2. 🔗 Agregar al PATH (si es necesario):
echo    - Win + X → Sistema → Configuración avanzada
echo    - Variables de entorno → Path → Editar
echo    - Agregar ruta de MySQL (ej: C:\xampp\mysql\bin)
echo.
echo 3. 🔄 Iniciar servicios:
echo    - XAMPP: Iniciar Apache y MySQL desde el panel
echo    - MySQL: net start mysql (como administrador)
echo.
echo 4. 🗄️ Crear base de datos:
echo    - Para MySQL SIN contraseña:
echo      mysql -u root -e "CREATE DATABASE citas_2;"
echo    - Para MySQL CON contraseña:
echo      mysql -u root -p
echo      CREATE DATABASE citas_2;
echo      USE citas_2;
echo      SOURCE ruta_del_archivo.sql;
echo.
echo 💡 Nota: XAMPP por defecto no tiene contraseña para root
echo    MySQL oficial sí requiere contraseña durante instalación
echo.
echo 5. 🧪 Probar backup:
echo    - Ejecuta: backup-bd.bat
echo    - Verifica que el archivo no esté vacío
echo.
pause
goto found_mysql

:exit
echo.
echo 👋 Configuración de MySQL completada
echo 💡 Ejecuta backup-bd.bat para probar el sistema
echo.
timeout /t 3 >nul
exit
