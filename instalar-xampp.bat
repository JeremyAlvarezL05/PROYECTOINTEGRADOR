@echo off
setlocal enabledelayedexpansion
title Instalar XAMPP - Sistema Clinico
color 0C

echo ========================================
echo        INSTALAR XAMPP
echo         Sistema Clinico Integrador
echo ========================================
echo.

echo 🎯 XAMPP es la forma más fácil de instalar MySQL
echo    📦 Incluye: Apache, MySQL, PHP y PhpMyAdmin
echo    🚀 Instalación simple y rápida
echo    🔧 Interfaz gráfica para gestión
echo.

echo ⚠️  NOTA: Este script te ayudará a descargar XAMPP
echo    pero la instalación debe hacerse manualmente
echo.

echo ========================================
echo          OPCIONES
echo ========================================
echo [1] Abrir página de descarga de XAMPP
echo [2] Mostrar instrucciones de instalación
echo [3] Verificar si XAMPP ya está instalado
echo [4] Configurar XAMPP después de instalar
echo [5] Salir
echo ========================================

choice /c 12345 /m "Seleccione opción:"

if %ERRORLEVEL%==1 goto download_xampp
if %ERRORLEVEL%==2 goto show_instructions
if %ERRORLEVEL%==3 goto check_xampp
if %ERRORLEVEL%==4 goto configure_xampp
if %ERRORLEVEL%==5 goto exit

:download_xampp
cls
echo ========================================
echo       DESCARGAR XAMPP
echo ========================================
echo.
echo 🌐 Abriendo página de descarga de XAMPP...
echo.
echo 📍 URL: https://www.apachefriends.org/download.html
echo.
echo 💡 Recomendaciones:
echo    - Descargar la versión más reciente
echo    - Elegir la versión para Windows
echo    - Tamaño aproximado: 150-200 MB
echo.

REM Abrir página de descarga
start https://www.apachefriends.org/download.html

echo ✅ Página abierta en el navegador
echo.
echo 📋 Pasos siguientes:
echo    1. Descargar XAMPP
echo    2. Ejecutar como administrador
echo    3. Seguir el asistente de instalación
echo    4. Volver a ejecutar este script (opción 4)
echo.
pause
goto inicio

:show_instructions
cls
echo ========================================
echo     INSTRUCCIONES DE INSTALACIÓN
echo ========================================
echo.
echo 📥 DESCARGAR:
echo    1. Ve a: https://www.apachefriends.org/
echo    2. Haz clic en "Download" para Windows
echo    3. Guarda el archivo xampp-windows-x64-X.X.X-installer.exe
echo.
echo 🔧 INSTALAR:
echo    1. Haz clic derecho en el archivo descargado
echo    2. Selecciona "Ejecutar como administrador"
echo    3. Sigue el asistente de instalación
echo    4. Carpeta recomendada: C:\xampp
echo    5. Instala todos los componentes (Apache, MySQL, PHP)
echo.
echo ⚙️  CONFIGURAR:
echo    1. Abre el "XAMPP Control Panel"
echo    2. Haz clic en "Start" para Apache
echo    3. Haz clic en "Start" para MySQL
echo    4. Verifica que ambos estén en verde
echo.
echo 🗄️ CREAR BASE DE DATOS:
echo    1. Abre http://localhost/phpmyadmin
echo    2. Haz clic en "Nueva" para crear BD
echo    3. Nombre: citas_2
echo    4. Haz clic en "Crear"
echo.
echo 🧪 PROBAR:
echo    1. Abre cmd y ejecuta: mysql --version
echo    2. Ejecuta: backup-bd.bat
echo    3. Verifica que el backup no esté vacío
echo.
pause
goto inicio

:check_xampp
cls
echo ========================================
echo      VERIFICAR XAMPP
echo ========================================
echo.
echo 🔍 Verificando instalación de XAMPP...
echo.

REM Verificar carpeta de XAMPP
if exist "C:\xampp" (
    echo ✅ XAMPP instalado en: C:\xampp
    echo.
    
    REM Verificar MySQL
    if exist "C:\xampp\mysql\bin\mysql.exe" (
        echo ✅ MySQL encontrado: C:\xampp\mysql\bin\mysql.exe
        
        REM Verificar versión
        echo.
        echo 📊 Versión de MySQL:
        "C:\xampp\mysql\bin\mysql.exe" --version 2>nul
        if %errorlevel% neq 0 (
            echo ❌ Error al obtener versión (MySQL puede no estar iniciado)
        )
    ) else (
        echo ❌ MySQL no encontrado en XAMPP
    )
    
    echo.
    REM Verificar Apache
    if exist "C:\xampp\apache\bin\httpd.exe" (
        echo ✅ Apache encontrado: C:\xampp\apache\bin\httpd.exe
    ) else (
        echo ❌ Apache no encontrado en XAMPP
    )
    
    echo.
    REM Verificar Control Panel
    if exist "C:\xampp\xampp-control.exe" (
        echo ✅ Control Panel: C:\xampp\xampp-control.exe
        echo.
        echo 🔧 Para gestionar XAMPP:
        echo    1. Ejecuta: C:\xampp\xampp-control.exe
        echo    2. Inicia Apache y MySQL
        echo    3. Ambos deben aparecer en verde
    ) else (
        echo ❌ Control Panel no encontrado
    )
    
    echo.
    echo 🌐 Verificar servicios web:
    echo    - Apache: http://localhost/
    echo    - phpMyAdmin: http://localhost/phpmyadmin/
    
) else (
    echo ❌ XAMPP no está instalado
    echo.
    echo 💡 Para instalar XAMPP:
    echo    1. Selecciona opción [1] para descargar
    echo    2. Sigue las instrucciones de instalación
    echo    3. Vuelve a verificar con esta opción
)

echo.
pause
goto inicio

:configure_xampp
cls
echo ========================================
echo      CONFIGURAR XAMPP
echo ========================================
echo.

if not exist "C:\xampp" (
    echo ❌ XAMPP no está instalado
    echo 💡 Primero instala XAMPP con la opción [1]
    echo.
    pause
    goto inicio
)

echo 🔧 Configurando XAMPP para el proyecto...
echo.

echo 📋 Pasos de configuración:
echo.
echo 1. 🚀 Iniciar servicios de XAMPP
echo.
echo 💡 Abriendo XAMPP Control Panel...
start C:\xampp\xampp-control.exe

echo.
echo ⏳ Espera a que se abra el Control Panel y luego:
echo    1. Haz clic en "Start" junto a Apache
echo    2. Haz clic en "Start" junto a MySQL
echo    3. Ambos deben aparecer en verde
echo.
pause

echo.
echo 2. 🗄️ Crear base de datos
echo.
echo 💡 Abriendo phpMyAdmin...
start http://localhost/phpmyadmin

echo.
echo ⏳ En phpMyAdmin:
echo    1. Haz clic en "Nueva" (lado izquierdo)
echo    2. Escribe "citas_2" como nombre
echo    3. Haz clic en "Crear"
echo.
pause

echo.
echo 3. 🔗 Configurar PATH (opcional)
echo.
set /p add_path=¿Agregar MySQL al PATH del sistema? (S/N): 
if /i "!add_path!"=="S" (
    echo.
    echo 📋 Instrucciones para agregar al PATH:
    echo    1. Win + X → Sistema → Configuración avanzada
    echo    2. Variables de entorno → Path → Editar
    echo    3. Nuevo → C:\xampp\mysql\bin
    echo    4. Aceptar en todas las ventanas
    echo    5. Reiniciar cmd
    echo.
    echo 💡 O ejecuta este comando temporal:
    echo    set PATH=C:\xampp\mysql\bin;%%PATH%%
)

echo.
echo 4. 🧪 Probar configuración
echo.
echo 🔄 Probando conexión a MySQL...
"C:\xampp\mysql\bin\mysql.exe" -u root -e "SHOW DATABASES;" 2>nul
if %errorlevel% equ 0 (
    echo ✅ Conexión exitosa a MySQL
) else (
    echo ❌ Error de conexión
    echo 💡 Verifica que MySQL esté iniciado en XAMPP
)

echo.
echo 🔄 Verificando base de datos citas_2...
"C:\xampp\mysql\bin\mysql.exe" -u root -e "USE citas_2; SELECT 'BD existe' as resultado;" 2>nul
if %errorlevel% equ 0 (
    echo ✅ Base de datos citas_2 disponible
) else (
    echo ❌ Base de datos citas_2 no encontrada
    echo 💡 Crea la base de datos en phpMyAdmin
)

echo.
echo ✅ Configuración completada
echo.
echo 📋 Resumen:
echo    - XAMPP Control Panel: C:\xampp\xampp-control.exe
echo    - phpMyAdmin: http://localhost/phpmyadmin
echo    - MySQL: C:\xampp\mysql\bin\mysql.exe
echo    - Base de datos: citas_2
echo.
echo 🧪 Ahora puedes probar: backup-bd.bat
echo.
pause
goto inicio

:inicio
cls
goto :eof

:exit
echo.
echo 👋 Instalación de XAMPP finalizada
echo 💡 Recuerda iniciar Apache y MySQL en XAMPP Control Panel
echo.
timeout /t 3 >nul
exit
