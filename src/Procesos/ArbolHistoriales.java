package Procesos;

import ModeloArreglo.HistorialClinico;

public class ArbolHistoriales {
    private NodoArbol raiz;

    private class NodoArbol {
        HistorialClinico historial;
        NodoArbol izquierdo, derecho;

        public NodoArbol(HistorialClinico historial) {
            this.historial = historial;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    public void insertar(HistorialClinico historial) {
        raiz = insertarRec(raiz, historial);
    }

    private NodoArbol insertarRec(NodoArbol nodo, HistorialClinico historial) {
        if (nodo == null) return new NodoArbol(historial);
        if (historial.getDni().compareTo(nodo.historial.getDni()) < 0)
            nodo.izquierdo = insertarRec(nodo.izquierdo, historial);
        else if (historial.getDni().compareTo(nodo.historial.getDni()) > 0)
            nodo.derecho = insertarRec(nodo.derecho, historial);
        return nodo;
    }

    public HistorialClinico buscar(String dni) {
        return buscarRec(raiz, dni);
    }

    private HistorialClinico buscarRec(NodoArbol nodo, String dni) {
        if (nodo == null || nodo.historial.getDni().equals(dni)) return nodo != null ? nodo.historial : null;
        if (dni.compareTo(nodo.historial.getDni()) < 0)
            return buscarRec(nodo.izquierdo, dni);
        return buscarRec(nodo.derecho, dni);
    }
}
