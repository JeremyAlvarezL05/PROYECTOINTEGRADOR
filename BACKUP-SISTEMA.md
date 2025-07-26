# 💾 Sistema de Backup - Base de Datos

## 🎯 **Sistema de Backup Completo**

### **📁 Scripts de Backup (3 archivos)**

#### **1. `backup-bd.bat` - Backup Principal**

- 💾 **Crea backup** de la base de datos
- 📅 **Nombre automático** con fecha y hora
- 🔄 **Menú interactivo** con opciones
- 📊 **Gestión de archivos** antiguos

#### **2. `limpiar-backups.bat` - Limpieza**

- 🧹 **Elimina backups antiguos**
- 📋 **Mantiene los 5 más recientes**
- ⚠️ **Confirmación de seguridad**
- 📊 **Reporte de limpieza**

#### **3. `restaurar-backup.bat` - Restauración**

- 🔄 **Restaura desde backup**
- 📂 **Lista backups disponibles**
- ⚠️ **Advertencias de seguridad**
- 📊 **Información detallada**

---

## 🚀 **Uso del Sistema**

### **💾 Crear Backup**

```batch
backup-bd.bat
```

**Resultado:**

```
📄 backup_citas_2_2025-07-17_14-30-45.sql
📏 Tamaño: [tamaño] bytes
📁 Ubicación: backups/
```

### **🔄 Restaurar Backup**

```batch
restaurar-backup.bat
```

**Proceso:**

1. Seleccionar archivo de backup
2. Confirmar restauración
3. Ejecutar restauración
4. Verificar resultado

### **🧹 Limpiar Backups**

```batch
limpiar-backups.bat
```

**Acción:**

- Mantiene 5 backups más recientes
- Elimina archivos antiguos
- Libera espacio en disco

---

## 📊 **Integración con Monitor**

### **🏥 Monitor Principal**

```batch
monitor-sistema.bat
```

**Nueva opción:**

```
[6] Backup base de datos
```

**Funcionalidad:**

- Ejecuta `backup-bd.bat`
- Regresa al monitor
- Integración completa

---

## 🎤 **Para tu Presentación**

### **Demostración de Backup (2 minutos)**

#### **1. Mostrar Sistema (30 seg)**

```batch
dir backup*.bat
```

- 3 scripts especializados
- Funciones específicas
- Integración con monitor

#### **2. Crear Backup (45 seg)**

```batch
backup-bd.bat
```

- Ejecutar script
- Mostrar archivo generado
- Explicar nombre automático

#### **3. Mostrar Integración (45 seg)**

```batch
monitor-sistema.bat
```

- Opción [6] Backup
- Funcionalidad integrada
- Monitoreo completo

---

## 🔧 **Características Técnicas**

### **✅ Automatización:**

- **Nombre automático:** `backup_citas_2_YYYY-MM-DD_HH-MM-SS.sql`
- **Timestamp único:** Sin sobreescritura
- **Directorio organizado:** `/backups/`
- **Validación de errores:** Manejo robusto

### **✅ Seguridad:**

- **Confirmación:** Antes de operaciones críticas
- **Advertencias:** Para restauraciones
- **Validación:** De archivos y credenciales
- **Rollback:** Posible con backups

### **✅ Gestión:**

- **Limpieza automática:** Sugerida >10 archivos
- **Información detallada:** Tamaño, fecha, etc.
- **Listado organizado:** Por fecha (más reciente primero)
- **Contador de archivos:** Para control

---

## 📋 **Configuración**

### **Variables del Sistema:**

```batch
set DB_NAME=citas_2
set DB_USER=root
set DB_PASSWORD=
set BACKUP_DIR=backups
```

### **Formato de Archivos:**

```
backup_citas_2_2025-07-17_14-30-45.sql
backup_citas_2_2025-07-17_15-45-12.sql
backup_citas_2_2025-07-17_16-20-33.sql
```

### **Estructura de Directorio:**

```
clinica-integrador/
├── backup-bd.bat
├── limpiar-backups.bat
├── restaurar-backup.bat
├── monitor-sistema.bat
└── backups/
    ├── backup_citas_2_2025-07-17_14-30-45.sql
    ├── backup_citas_2_2025-07-17_15-45-12.sql
    └── backup_citas_2_2025-07-17_16-20-33.sql
```

---

## 🎓 **Justificación Académica**

### **"Implementamos sistema de backup porque:"**

1. **💾 Protección de datos** - Prevención de pérdida
2. **🔄 Recuperación rápida** - Restauración automática
3. **📊 Gestión profesional** - Herramientas especializadas
4. **🎮 Interfaz intuitiva** - Menús interactivos
5. **📈 Escalabilidad** - Fácil agregar funciones

### **✅ Conceptos Demostrados:**

- **Automatización** de tareas críticas
- **Gestión de archivos** con timestamps
- **Validación de errores** y manejo robusto
- **Interfaces de usuario** intuitivas
- **Integración** con sistemas existentes

---

## 💡 **Comandos para Presentación**

### **Demostración Rápida:**

```batch
# 1. Crear backup
backup-bd.bat

# 2. Ver archivos generados
dir backups\

# 3. Mostrar integración
monitor-sistema.bat
```

### **Demostración Completa:**

```batch
# 1. Sistema completo
dir backup*.bat

# 2. Crear backup
backup-bd.bat

# 3. Mostrar gestión
limpiar-backups.bat

# 4. Integración con monitor
monitor-sistema.bat
```

---

## 🏆 **Resultado Final**

### **✅ Sistema Completo:**

- **3 scripts especializados**
- **Integración con monitor**
- **Gestión automática**
- **Interfaces intuitivas**

### **✅ Funcionalidad Profesional:**

- **Backup automático** con timestamps
- **Limpieza inteligente** de archivos
- **Restauración segura** con confirmación
- **Integración completa** con monitoreo

**¡Perfecto para demostrar conocimiento de administración de bases de datos!** 🎯
