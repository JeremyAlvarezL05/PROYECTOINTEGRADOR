package EstructurasListas;

import java.io.Serializable;

public class Nodo implements Serializable {
    private Pacientes paciente;
    private Doctor doctor;
    private Nodo sig;

    public Nodo(Pacientes paciente) {
        this.paciente = paciente;
        this.sig = null;
    }

    public Nodo(Doctor doctor) {
        this.doctor = doctor;
        this.sig = null;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }
}
