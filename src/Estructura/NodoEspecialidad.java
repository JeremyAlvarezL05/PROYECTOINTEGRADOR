package Estructura;

import Modelo.*;
import java.io.Serializable;

public class NodoEspecialidad implements Serializable{
    private Especialidad elemento;
    private NodoEspecialidad Izq;
    private NodoEspecialidad Der;

    public NodoEspecialidad(Especialidad elemento){
        this.elemento = elemento;
        Izq = Der = null;
    }

    public Especialidad getElemento() {
        return elemento;
    }

    public void setElemento(Especialidad elemento) {
        this.elemento = elemento;
    }

    public NodoEspecialidad getIzq() {
        return Izq;
    }

    public void setIzq(NodoEspecialidad Izq) {
        this.Izq = Izq;
    }

    public NodoEspecialidad getDer() {
        return Der;
    }

    public void setDer(NodoEspecialidad Der) {
        this.Der = Der;
    }
} // Fin de la clase
