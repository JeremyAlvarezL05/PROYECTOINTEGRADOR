/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamientos;

import EstructurasListas.Nodo;

public class Pila {
    private Nodo cima;

    public Pila() {
        this.cima = null;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public void apilar(Nodo nodo) {
        nodo.setSig(cima);
        cima = nodo;
    }

    public Nodo desapilar() {
        if (estaVacia()) {
            return null;
        }
        Nodo nodo = cima;
        cima = cima.getSig();
        nodo.setSig(null); // Importante para evitar referencias circulares
        return nodo;
    }

    public Nodo cima() {
        return cima;
    }
}
