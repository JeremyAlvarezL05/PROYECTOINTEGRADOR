package ModeloArreglo;

import java.io.Serializable;
import java.util.Objects;

public class HistorialClinico implements Serializable {
    private String dni;
    private String nombre;
    private String especialidad;
    private String tratamiento;
    private String doctor;
    private String motivoConsulta;
    private String diagnostico;
    private String fecha; // Nuevo campo

    // Constructor vacío
    public HistorialClinico() {}

    // Constructor completo
    public HistorialClinico(String dni, String nombre, String especialidad, String tratamiento,
                            String doctor, String motivoConsulta, String diagnostico, String fecha) {
        setDni(dni);
        setNombre(nombre);
        setEspecialidad(especialidad);
        setTratamiento(tratamiento);
        setDoctor(doctor);
        setMotivoConsulta(motivoConsulta);
        setDiagnostico(diagnostico);
        setFecha(fecha);
    }

    /**
     * Devuelve los datos del historial como un arreglo de objetos para ser utilizado en tablas.
     * @param num Número de fila.
     * @return Array con los datos del historial.
     */
    public Object[] Registro(int num) {
        return new Object[]{num, dni, nombre, especialidad, tratamiento, doctor, motivoConsulta, diagnostico, fecha};
    }

    /**
     * Método toString mejorado para representar un historial clínico como texto.
     * @return Representación en formato texto.
     */
    @Override
    public String toString() {
        return "=== Historial Clínico ===" +
               "\nDNI                : " + dni +
               "\nNombre             : " + nombre +
               "\nEspecialidad       : " + especialidad +
               "\nTratamiento        : " + tratamiento +
               "\nDoctor             : " + doctor +
               "\nMotivo de Consulta : " + motivoConsulta +
               "\nDiagnóstico        : " + diagnostico +
               "\nFecha              : " + fecha +
               "\n=======================";
    }

    /**
     * Validación de igualdad basada en el DNI, ya que se asume como identificador único.
     * @param o Objeto a comparar.
     * @return True si ambos objetos representan el mismo historial clínico.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialClinico that = (HistorialClinico) o;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    // === Getter y Setter con validaciones ===

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("DNI debe contener exactamente 8 dígitos.");
        }
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
        this.especialidad = especialidad;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        if (tratamiento == null || tratamiento.trim().isEmpty()) {
            throw new IllegalArgumentException("El tratamiento no puede estar vacío.");
        }
        this.tratamiento = tratamiento;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        if (doctor == null || doctor.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del doctor no puede estar vacío.");
        }
        this.doctor = doctor;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        if (motivoConsulta == null || motivoConsulta.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo de consulta no puede estar vacío.");
        }
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico no puede estar vacío.");
        }
        this.diagnostico = diagnostico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || !fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("La fecha debe estar en el formato DD/MM/AAAA.");
        }
        this.fecha = fecha;
    }
}
