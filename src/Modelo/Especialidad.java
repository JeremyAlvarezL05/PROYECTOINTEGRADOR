package Modelo;

import java.io.Serializable;

public class Especialidad implements Serializable{
    private String espe;
    
    public Especialidad(){}

    public String getEspe() {     
        return espe;    
    }

    public void setEspe(String espe) {
        this.espe = espe;  
    }

    // Métodos adicionales
    public Especialidad(Object[] registro){
        this.espe = registro[0].toString();
    }

    public Object[] getRegistro(){
        Object[] fila = {espe};
        return fila;
    }
} // Fin de la clase
