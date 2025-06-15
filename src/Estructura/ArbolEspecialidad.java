package Estructura;

import Modelo.*;
import java.io.Serializable;
import javax.swing.table.DefaultTableModel;

public class ArbolEspecialidad implements Serializable {
    private NodoEspecialidad Raiz;

    public ArbolEspecialidad() {
        Raiz = null;
    }

    public NodoEspecialidad getRaiz() {
        return Raiz;
    }

    public void setRaiz(NodoEspecialidad Raiz) {
        this.Raiz = Raiz;
    }

    // Método que inserta el nodo especialidad al árbol ordenándolo por la especialidad
    public NodoEspecialidad Agregar(NodoEspecialidad Nodo, Especialidad elemento) {
        if (Nodo == null) {
            NodoEspecialidad Nuevo = new NodoEspecialidad(elemento);
            return Nuevo;
        } else {
            if (elemento.getEspe().compareTo(Nodo.getElemento().getEspe()) > 0) {
                Nodo.setDer(Agregar(Nodo.getDer(), elemento));
            } else {
                Nodo.setIzq(Agregar(Nodo.getIzq(), elemento));
            }
        }
        return Nodo;
    } // Fin del método

    // Método que muestra los datos del árbol en una tabla
    public void Listar_InOrder(NodoEspecialidad Nodo, DefaultTableModel modelo) {
        if (Nodo != null) {
            Listar_InOrder(Nodo.getIzq(), modelo);
            modelo.addRow(Nodo.getElemento().getRegistro());
            Listar_InOrder(Nodo.getDer(), modelo);
        }
    } // Fin mostrar

    // Método que busca una especialidad
    public NodoEspecialidad BuscarEspe(String dato) {
        NodoEspecialidad aux = Raiz;
        while (aux != null) {
            if (aux.getElemento().getEspe().startsWith(dato)) {
                return aux;
            } else {
                if (dato.compareToIgnoreCase(aux.getElemento().getEspe()) > 0) {
                    aux = aux.getDer();
                } else {
                    aux = aux.getIzq();
                }
            }
        }
        return null;
    } // Fin buscar

    // Método que busca el mayor elemento del subárbol izquierdo
    public NodoEspecialidad BuscarMayorIzquierda(NodoEspecialidad auxiliar) {
        if (auxiliar != null) {
            while (auxiliar.getDer() != null) {
                auxiliar = auxiliar.getDer();
            }
        }
        return auxiliar;
    } // Fin buscar mayor

    // Método que elimina el mayor elemento del subárbol izquierdo
    public NodoEspecialidad EliminarMayorIzquierda(NodoEspecialidad auxiliar) {
        if (auxiliar == null) {
            return null;
        } else if (auxiliar.getDer() != null) {
            auxiliar.setDer(EliminarMayorIzquierda(auxiliar.getDer()));
            return auxiliar;
        }
        return auxiliar.getIzq();
    } // Fin eliminar mayor

    // Método que elimina un nodo del árbol
    public NodoEspecialidad Eliminar(NodoEspecialidad auxiliar, String dato) {
        if (auxiliar == null) {
            return null;
        }
        if (dato.compareTo(auxiliar.getElemento().getEspe()) < 0) {
            auxiliar.setIzq(Eliminar(auxiliar.getIzq(), dato));
        } else if (dato.compareTo(auxiliar.getElemento().getEspe()) > 0) {
            auxiliar.setDer(Eliminar(auxiliar.getDer(), dato));
        } else if (auxiliar.getIzq() != null && auxiliar.getDer() != null) {
            auxiliar.setElemento(BuscarMayorIzquierda(auxiliar.getIzq()).getElemento());
            auxiliar.setIzq(EliminarMayorIzquierda(auxiliar.getIzq()));
        } else {
            auxiliar = (auxiliar.getIzq() != null) ? auxiliar.getIzq() : auxiliar.getDer();
        }
        return auxiliar;
    } // Fin eliminar
} // Fin de la clase
