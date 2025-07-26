# 🏥 Sistema de Gestión Clínica - Integrador UTP

Un sistema integral de gestión clínica desarrollado en Java con interfaz Swing, diseñado para administrar pacientes, médicos, citas e historias clínicas de manera eficiente y segura.

## 🚀 Características Principales

- **Gestión de Pacientes**: Registro, edición y consulta de información de pacientes
- **Gestión de Médicos**: Administración de profesionales médicos y especialidades
- **Sistema de Citas**: Programación y seguimiento de citas médicas con cancelación automática
- **Historias Clínicas**: Registro completo del historial médico de pacientes
- **Autenticación y Autorización**: Sistema de usuarios con roles diferenciados
- **Auditoría**: Registro detallado de todas las acciones del sistema
- **Interfaz Intuitiva**: UI moderna desarrollada con Java Swing
- **Cron Job Nativo**: Sistema de tareas programadas en Java
- **Monitoreo Avanzado**: Sistema de monitoreo en tiempo real
- **Backup Automatizado**: Gestión completa de backups de base de datos

## 🛠️ Tecnologías Utilizadas

- **Java 22**: Lenguaje principal de desarrollo
- **Swing**: Framework para la interfaz gráfica
- **MySQL**: Base de datos relacional
- **Maven**: Gestión de dependencias y construcción
- **Logback**: Sistema de logging
- **JUnit**: Framework de testing
- **ScheduledExecutorService**: Para tareas programadas
- **Apache Commons**: Librerías utilitarias
- **Google Guava**: Colecciones y utilidades adicionales

## 📁 Estructura del Proyecto

```
clinica-integrador/
├── src/main/java/
│   ├── Controlador/     # Controladores MVC
│   ├── service/         # Lógica de negocio (incluye TareaProgramadaService)
│   ├── DAO/            # Acceso a datos
│   ├── model/          # Modelos de dominio
│   ├── Vista/          # Interfaces de usuario
│   ├── util/           # Utilidades y helpers
│   ├── enums/          # Enumeraciones
│   └── Main/           # Aplicaciones principales
├── src/main/resources/  # Recursos y configuración
├── src/test/           # Pruebas unitarias y TDD
├── logs/               # Archivos de log
├── backups/            # Backups automáticos de BD
└── scripts/            # Scripts de despliegue y monitoreo
```

## ⚡ Inicio Rápido

### Requisitos Previos

- Java 22 o superior
- Maven 3.6+
- MySQL 5.7+ o MariaDB 10.2+ (recomendado: XAMPP)

### 🚀 Instalación y Despliegue

#### 1. Despliegue Automático (Recomendado)

```bash
# Desplegar aplicación completa
deploy-production.bat
```

**Resultado:**

- ✅ JAR ejecutable de 36MB
- ✅ Scripts de inicio para desarrollo y producción
- ✅ Configuración MySQL incluida
- ✅ Aplicación lista en `C:\ClinicaApp\`

#### 2. Configuración de Base de Datos

```bash
# Instalar XAMPP (incluye MySQL)
instalar-xampp.bat

# O ejecutar script SQL directamente
mysql -u root -p < configurar-mysql.sql
```

#### 3. Iniciar la Aplicación

```bash
# Para desarrollo (con consola)
C:\ClinicaApp\iniciar.bat

# Para usuario final (sin consola)
C:\ClinicaApp\iniciar-silencioso.bat
```

### 🎮 Monitoreo del Sistema

```bash
# Monitor interactivo con verificaciones automáticas
monitor-sistema.bat
```

**Características del Monitor:**

- ✅ Verificación automática cada 30 segundos
- ✅ Estado de Java, MySQL, aplicación
- ✅ Métricas de sistema y logs
- ✅ Opciones interactivas para gestión

## 🕒 Sistema de Cron Job Nativo

### Características

- **Ejecución automática**: Diaria a las 00:30 AM
- **Cancelación inteligente**: Citas no atendidas del día anterior
- **Implementación Java**: ScheduledExecutorService (no depende de scripts externos)
- **Interfaz gráfica**: Monitor en tiempo real con Swing
- **Configuración flexible**: Horarios personalizables

### Archivos Principales

| Archivo                          | Descripción                       |
| -------------------------------- | --------------------------------- |
| `TareaProgramadaService.java`    | Servicio principal con scheduler  |
| `TareaProgramadaController.java` | Controlador para interfaz gráfica |
| `MainTareaProgramada.java`       | Aplicación de demostración        |
| `CitaService.java`               | Lógica de cancelación de citas    |
| `CitaDAO.java`                   | Consultas de citas vencidas       |

### Demo del Cron Job

```bash
# Ejecutar aplicación de demostración
java -cp target/classes Main.MainTareaProgramada
```

**Opciones del menú:**

1. Ver estadísticas del sistema
2. Ejecutar cancelación manual
3. Abrir monitor gráfico
4. Información del servicio
5. Probar con datos de ejemplo
6. Salir

## 💾 Sistema de Backup Automatizado

### Scripts de Backup

```bash
# Crear backup automático
backup-bd.bat

# Gestionar backups antiguos
limpiar-backups.bat

# Restaurar desde backup
restaurar-backup.bat
```

### Características

- **Backups automáticos** con timestamps
- **Gestión inteligente** de archivos (mantiene 5 más recientes)
- **Restauración segura** con confirmaciones
- **Integración** con el sistema de monitoreo

## 🧪 Testing y Calidad de Software

### Tipos de Testing Implementados

#### 1. **TDD (Test-Driven Development)**

- Archivo: `RealTddTest.java`
- Metodología Red-Green-Refactor
- Validaciones de citas médicas

#### 2. **Pruebas de Calidad de Software**

- Archivo: `SoftwareQualityTest.java`
- Casos límite y robustez
- Validaciones de entidades

#### 3. **Pruebas de Seguridad**

- Archivo: `SecurityTest.java`
- Validación de contraseñas
- Protección contra ataques

### Ejecutar Tests

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar tests específicos
mvn test -Dtest=RealTddTest
mvn test -Dtest=SoftwareQualityTest
mvn test -Dtest=SecurityTest
```

## � Seguridad y Auditoría

### Características de Seguridad

- **Autenticación**: Sistema de login con validación
- **Autorización**: Control de acceso basado en roles
- **Encriptación**: Contraseñas hasheadas con salt
- **Auditoría**: Registro completo en `logs/audit.log`
- **Validación**: Validación robusta de datos de entrada

### Logs del Sistema

| Tipo           | Archivo            | Descripción                      |
| -------------- | ------------------ | -------------------------------- |
| **Aplicación** | `logs/clinica.log` | Logs generales del sistema       |
| **Auditoría**  | `logs/audit.log`   | Acciones de usuarios y seguridad |
| **Cron Job**   | Integrado          | Logs de tareas programadas       |

## � Documentación Técnica

### Documentación Unificada

La documentación técnica está organizada en la carpeta `docs/` con archivos unificados:

- **[CRONJOB-SISTEMA.md](docs/CRONJOB-SISTEMA.md)** - Cron job nativo completo
- **[DESPLIEGUE-MONITOREO.md](docs/DESPLIEGUE-MONITOREO.md)** - Despliegue y monitoreo
- **[TESTING-CALIDAD.md](docs/TESTING-CALIDAD.md)** - Testing y calidad completo
- **[BACKUP-SOLUCIONES.md](docs/BACKUP-SOLUCIONES.md)** - Backup y soluciones
- **[MANUAL-ACADEMICO.md](docs/MANUAL-ACADEMICO.md)** - Manual académico
- **[PRESENTACION.md](docs/PRESENTACION.md)** - Guía de presentación

### Índice Completo

Ver **[DOCUMENTACION-TECNICA.md](DOCUMENTACION-TECNICA.md)** para el índice completo de toda la documentación técnica disponible.

## �👥 Usuarios por Defecto

El sistema incluye usuarios predefinidos para testing:

| Usuario   | Contraseña | Rol           |
| --------- | ---------- | ------------- |
| admin     | admin123   | Administrador |
| doctor    | doctor123  | Médico        |
| recepcion | recep123   | Recepcionista |

## � Dashboard y Reportes

- Panel de control con métricas principales
- Reportes de citas por período
- Estadísticas de pacientes y médicos
- Historial de acciones recientes
- Monitor de tareas programadas en tiempo real

## 🎯 Casos de Uso Principales

1. **Registro de Paciente**: Nuevo paciente en el sistema
2. **Programación de Cita**: Asignación de cita médica
3. **Consulta Médica**: Atención y registro en historia clínica
4. **Gestión de Médicos**: Administración de profesionales
5. **Auditoría**: Seguimiento de actividades del sistema
6. **Cancelación Automática**: Citas no atendidas procesadas automáticamente
7. **Backup y Restauración**: Gestión de datos del sistema

## 🎤 Para Presentación Académica

### Demostración Completa (10 minutos)

#### 1. **Despliegue Profesional** (2 min)

```bash
deploy-production.bat
```

- Compilación Maven automática
- JAR ejecutable de 36MB
- Scripts de inicio generados

#### 2. **Monitoreo Avanzado** (3 min)

```bash
monitor-sistema.bat
```

- Verificaciones automáticas
- Métricas en tiempo real
- Gestión de logs

#### 3. **Cron Job Nativo** (3 min)

```bash
java -cp target/classes Main.MainTareaProgramada
```

- Demostración de cancelación automática
- Interfaz gráfica de monitoreo
- Estadísticas del sistema

#### 4. **Sistema de Backup** (2 min)

```bash
backup-bd.bat
```

- Backup automático con timestamps
- Gestión inteligente de archivos
- Restauración segura

### Números para Impresionar

- 📦 **48 dependencias** gestionadas automáticamente
- ✅ **50+ tests** implementados (TDD, calidad, seguridad)
- 🔧 **88 archivos** compilados correctamente
- 💾 **36MB** JAR ejecutable final
- 🕒 **Cron job nativo** sin dependencias externas
- 🏥 **7 opciones** de monitoreo integradas
- 💾 **Backup automático** con gestión inteligente
- 🔐 **3 tipos de logs** implementados

### Conceptos Técnicos Demostrados

- **Maven**: Gestión de dependencias y build automation
- **ScheduledExecutorService**: Programación de tareas nativas
- **Swing**: Interfaces gráficas profesionales
- **TDD**: Metodología de desarrollo dirigida por tests
- **Patrones de diseño**: MVC, DAO, Service Layer
- **Logging**: Sistema de logs estructurado
- **Backup/Restore**: Gestión de datos empresarial

## 🔧 Comandos Útiles

```bash
# Compilar proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Crear JAR ejecutable
mvn clean package -DskipTests

# Desplegar aplicación
deploy-production.bat

# Monitorear sistema
monitor-sistema.bat

# Backup de base de datos
backup-bd.bat

# Ejecutar cron job demo
java -cp target/classes Main.MainTareaProgramada

# Ejecutar aplicación principal
java -cp target/classes Main.Main
```

## 🎯 Valor Académico

Este proyecto demuestra:

### **Técnicas Avanzadas de Java**

- Programación concurrente con `ScheduledExecutorService`
- Manejo de threads y sincronización
- Interfaces gráficas con Swing
- Arquitectura por capas (MVC, Service, DAO)

### **Ingeniería de Software**

- Test-Driven Development (TDD)
- Pruebas de calidad y seguridad
- Gestión de configuración con Maven
- Principios SOLID y patrones de diseño

### **Operaciones y Monitoreo**

- Logging estructurado
- Métricas de sistema
- Backup automatizado
- Monitoreo en tiempo real

### **Despliegue y Distribución**

- Empaquetado con Maven
- Scripts de despliegue automático
- Configuración de entornos
- Documentación técnica completa

---

**Proyecto Académico** - Curso Integrador I: Sistemas Software  
**Universidad Tecnológica del Perú (UTP)**

**🏆 Sistema completo con implementación profesional de:**

- ✅ Cron job nativo en Java
- ✅ Sistema de monitoreo avanzado
- ✅ Backup automatizado
- ✅ Testing completo (TDD + Calidad + Seguridad)
- ✅ Despliegue profesional con Maven
