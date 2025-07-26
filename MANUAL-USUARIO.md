# 📋 Manual de Usuario - Sistema de Gestión Clínica

## 🚀 Inicio Rápido

### 1. Acceso al Sistema

**Iniciar la aplicación:**

```bash
# Doble clic en el archivo
iniciar.bat
```

**Credenciales por defecto:**
| Usuario | Contraseña | Rol |
|------------|------------|---------------|
| `admin` | `admin123` | Administrador |
| `doctor` | `doc123` | Médico |
| `recep` | `recep123` | Recepcionista |

### 2. Pantalla de Login

- Ingresa tu **usuario** y **contraseña**
- Haz clic en **"Iniciar Sesión"**
- El sistema te redirigirá al dashboard principal

---

## 🏥 Dashboard Principal

Al ingresar verás el menú lateral con las siguientes opciones:

| Módulo                    | Descripción                          | Acceso              |
| ------------------------- | ------------------------------------ | ------------------- |
| **👥 Pacientes**          | Gestionar información de pacientes   | Todos los roles     |
| **👨‍⚕️ Médicos**            | Administrar médicos y especialidades | Admin/Recepcionista |
| **📅 Citas**              | Programar y gestionar citas médicas  | Todos los roles     |
| **📋 Historias Clínicas** | Registros médicos de pacientes       | Médicos/Admin       |
| **📊 Reportes**           | Generar reportes del sistema         | Admin/Médicos       |
| **⚙️ Usuarios**           | Gestionar usuarios del sistema       | Solo Admin          |
| **📋 Acciones**           | Ver auditoría del sistema            | Solo Admin          |
| **⏰ Programación**       | Monitorear tareas automáticas        | Solo Admin          |

---

## 👥 Gestión de Pacientes

### Agregar Nuevo Paciente

1. Clic en **"Pacientes"** del menú lateral
2. Clic en botón **"Agregar"**
3. Completa el formulario:
   - **Datos personales**: Nombre, apellido, DNI
   - **Contacto**: Email, teléfono, dirección
   - **Información médica**: Fecha nacimiento, sexo
4. Clic en **"Guardar"**

### Buscar Paciente

1. En el campo **"Filtrar"** ingresa el DNI
2. Clic en **"Buscar"**
3. Los resultados aparecen en la tabla

### Editar/Eliminar

- **Editar**: Selecciona un paciente y clic en **"Editar"**
- **Eliminar**: Selecciona un paciente y clic en **"Eliminar"**

---

## 👨‍⚕️ Gestión de Médicos

### Agregar Médico

1. Clic en **"Médicos"** del menú
2. Clic en **"Agregar"**
3. Completa:
   - **Datos personales**: Nombre, apellido, DNI
   - **Información profesional**: Especialidad, horarios
   - **Contacto**: Email, teléfono
4. Clic en **"Guardar"**

### Filtrar Médicos

- Usa el campo **"Filtrar"** para buscar por nombre o especialidad

---

## 📅 Gestión de Citas

### Programar Nueva Cita

1. Clic en **"Citas"** del menú
2. Clic en **"Agregar"**
3. Selecciona:
   - **Paciente**: De la lista desplegable
   - **Médico**: Según especialidad requerida
   - **Fecha y hora**: Usar el calendario
   - **Motivo**: Descripción de la consulta
4. Clic en **"Programar"**

### Estados de Citas

- **🔄 Programada**: Cita agendada
- **✅ Atendida**: Consulta realizada
- **❌ Cancelada**: Cita cancelada
- **⏰ No Asistió**: Paciente no se presentó

### Filtrar Citas

- **Por estado**: Usa el filtro desplegable
- **Por fecha**: Selecciona rango de fechas
- **Por texto**: Busca por paciente o médico

---

## 📋 Historias Clínicas

### Crear Historia Clínica

1. Clic en **"Historias Clínicas"**
2. Clic en **"Agregar"**
3. Selecciona el **paciente**
4. Completa:
   - **Síntomas**: Descripción detallada
   - **Diagnóstico**: Evaluación médica
   - **Tratamiento**: Indicaciones y medicamentos
5. Clic en **"Guardar"**

### Consultar Historias

- Selecciona un **paciente** del filtro
- La tabla muestra todas sus consultas
- Clic en **"Ver"** para detalles completos

---

## 📊 Reportes

### Generar Reportes

1. Clic en **"Reportes"**
2. Selecciona el tipo:
   - **📋 Pacientes**: Lista completa de pacientes
   - **👨‍⚕️ Médicos**: Información de médicos
   - **📅 Citas**: Reporte de citas por período
3. Configura filtros:
   - **Fechas**: Rango específico
   - **Paciente/Médico**: Filtro específico
4. Clic en **"Mostrar"**
5. Clic en **"Exportar"** para descargar Excel

---

## ⚙️ Administración (Solo Admin)

### Gestión de Usuarios

1. Clic en **"Usuarios"**
2. **Agregar usuario**:
   - Username, password
   - Rol (Admin/Médico/Recepcionista)
   - Datos personales
3. **Editar/Eliminar**: Seleccionar usuario y usar botones

### Auditoría del Sistema

1. Clic en **"Acciones"**
2. Ver registro de:
   - **Logins** de usuarios
   - **Operaciones** realizadas
   - **Fechas y horas** de actividad
3. **Filtros disponibles**:
   - Por usuario
   - Por tipo de acción
   - Por rango de fechas

### Monitoreo de Sistema

1. Clic en **"Programación"**
2. Ver estado de:
   - **Cancelación automática** de citas
   - **Backups** de base de datos
   - **Logs** del sistema

---

## 🔧 Funcionalidades Automáticas

### Cancelación Automática de Citas

- **¿Cuándo?**: Todos los días a las 00:30 AM
- **¿Qué hace?**: Cancela citas del día anterior que no fueron atendidas
- **Estado**: Cambia a "No Asistió" automáticamente

### Backup Automático

- **Frecuencia**: Diario
- **Ubicación**: Carpeta `backups/`
- **Retención**: Últimos 5 backups

---

## ❓ Preguntas Frecuentes

### ¿Olvidé mi contraseña?

- Contacta al administrador del sistema
- Solo el admin puede restablecer contraseñas

### ¿Puedo cancelar una cita?

- **Sí**: Selecciona la cita y cambia estado a "Cancelada"
- **Automático**: Las citas no atendidas se cancelan automáticamente

### ¿Cómo exportar datos?

- Usa el botón **"Exportar"** en cualquier reporte
- El archivo Excel se descarga automáticamente

### ¿Qué pasa si hay un error?

- **Revisa logs**: Menú "Programación"
- **Contacta soporte**: Reporta el error con detalles
- **Reinicia aplicación**: Si persiste el problema

---

## 🆘 Soporte Técnico

### Información del Sistema

- **Versión**: 1.0
- **Base de datos**: MySQL
- **Logs**: Carpeta `logs/`

### Contacto

- **Desarrollador**: [Tu información de contacto]
- **Documentación técnica**: Ver `docs/` para detalles avanzados

---

## 🎯 Consejos de Uso

### Para Mejores Resultados:

1. **Backup regular**: Verifica que los backups se ejecuten
2. **Usuarios únicos**: No compartas credenciales
3. **Datos completos**: Llena todos los campos requeridos
4. **Horarios**: Respeta los horarios de los médicos
5. **Seguimiento**: Usa las historias clínicas para continuidad

### Atajos Útiles:

- **Buscar rápido**: Usa los filtros en cada módulo
- **Exportar**: Siempre disponible en reportes
- **Historial**: Consulta "Acciones" para auditoría
- **Estado actual**: Monitorea en "Programación"

---

_Manual de Usuario v1.0 - Sistema de Gestión Clínica_
