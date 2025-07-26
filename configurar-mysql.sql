-- Script simplificado para configurar MySQL
-- Ejecutar desde MySQL Workbench o línea de comandos como root

-- 1. Crear base de datos
CREATE DATABASE IF NOT EXISTS citas_2;

-- 2. Usar la base de datos (con usuario root)
USE citas_2;

-- 3. Crear tablas básicas
CREATE TABLE usuario
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50)                                NOT NULL UNIQUE,
    password_hash VARCHAR(255)                               NOT NULL,
    rol           ENUM ('medico', 'administrativo', 'admin') NOT NULL,
    estado        BOOLEAN   DEFAULT TRUE,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla: Paciente
CREATE TABLE paciente
(
    dni              VARCHAR(8) PRIMARY KEY,
    nombre           VARCHAR(50)             NOT NULL,
    apellido         VARCHAR(50)             NOT NULL,
    fecha_nacimiento DATE                    NOT NULL,
    sexo             ENUM ('M', 'F', 'Otro') NOT NULL,
    direccion        VARCHAR(100),
    telefono         VARCHAR(15),
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla: Médico
CREATE TABLE medico
(
    id           INT PRIMARY KEY,
    nombre       VARCHAR(50) NOT NULL,
    apellido     VARCHAR(50) NOT NULL,
    especialidad VARCHAR(50) NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla: Historia Clínica
CREATE TABLE historia_clinica
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    paciente_dni VARCHAR(8),
    fecha        DATE NOT NULL,
    diagnostico  TEXT NOT NULL,
    medico_id    INT,
    especialidad VARCHAR(50),
    sintomas        TEXT,
    tratamiento      TEXT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_dni) REFERENCES paciente (dni) ON DELETE CASCADE,
    FOREIGN KEY (medico_id) REFERENCES medico (id) ON DELETE CASCADE
);

-- Tabla: Cita Médica
CREATE TABLE cita
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    paciente_dni VARCHAR(8) NOT NULL,
    medico_id    INT        NOT NULL,
    fecha_hora   DATETIME   NOT NULL,
    estado       ENUM ('pendiente', 'atendida', 'cancelada') DEFAULT 'pendiente',
    created_at   TIMESTAMP                                   DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP                                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_dni) REFERENCES paciente (dni) ON DELETE CASCADE,
    FOREIGN KEY (medico_id) REFERENCES medico (id) ON DELETE CASCADE
);

-- Tabla: Acciones recientes (para pila de acciones)
CREATE TABLE accion_reciente
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id  INT                                         NOT NULL,
    descripcion TEXT                                        NOT NULL,
    tipo        ENUM ('registro', 'edicion', 'eliminacion') NOT NULL,
    timestamp   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE
);

-- Tabla: Programación de Médicos
CREATE TABLE programacion_medico (
                                     id             INT AUTO_INCREMENT PRIMARY KEY,
                                     medico_id      INT NOT NULL,
                                     fecha          DATE NOT NULL,
                                     turno          ENUM ('mañana', 'mediodía', 'tarde') NOT NULL,
                                     created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     FOREIGN KEY (medico_id) REFERENCES medico(id) ON DELETE CASCADE
);