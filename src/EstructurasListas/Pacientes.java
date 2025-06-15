package EstructurasListas;

import java.io.Serializable;

public class Pacientes implements Serializable {

    private String nombres;
    private String dni;
    private String sintoma;
    private long telefono;
    private double temperatura;
    private double presion;
    private double peso;
    private double talla;
    private String alergias;
    private String motivoConsulta;  // Nuevo campo agregado

    public Pacientes() {
    }

    public Object[] Registro(int num) {
        Object fila[] = {num, dni, nombres, sintoma, telefono, temperatura, presion, peso, talla, alergias, motivoConsulta};
        return fila;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPresion() {
        return presion;
    }

    public void setPresion(double presion) {
        this.presion = presion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getMotivoConsulta() {  // Nuevo getter
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {  // Nuevo setter
        this.motivoConsulta = motivoConsulta;
    }
}
