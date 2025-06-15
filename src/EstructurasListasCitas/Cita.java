package EstructurasListasCitas;

import java.io.Serializable;

public class Cita implements Serializable {
    private String idCita;
    private String horaCita;
    private String nombrePaciente;
    private String dnipaciente;
    private String nombreDoctor;
    private String estadoCita; // Cambiar a estadoCita
    private String motivoCita;

    // Constructor por defecto
    public Cita() {
    }

    public Object[] Registro(int num) {
        Object fila[] = {
            num,
            idCita,
            horaCita,
            nombrePaciente,
            dnipaciente,
            nombreDoctor,
            estadoCita, // Cambiar a estadoCita
            motivoCita
        };
        return fila;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getDnipaciente() {
        return dnipaciente;
    }

    public void setDnipaciente(String dnipaciente) {
        this.dnipaciente = dnipaciente;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }
}
