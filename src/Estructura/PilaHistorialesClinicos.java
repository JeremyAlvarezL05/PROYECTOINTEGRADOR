package Estructura;

import ModeloArreglo.HistorialClinico;
import java.util.Stack;

public class PilaHistorialesClinicos {
    
    private Stack<HistorialClinico> pila;

    public PilaHistorialesClinicos() {
        pila = new Stack<>();
    }

    public void agregarHistorialClinico(HistorialClinico historial) {
        pila.push(historial);
    }

    public HistorialClinico obtenerHistorialClinico() {
        if (!pila.isEmpty()) {
            return pila.pop();
        }
        return null;
    }

    public boolean estaVacia() {
        return pila.isEmpty();
    }
}

