package Busquedas;

import ModeloArreglo.HistorialClinico;

public class BusquedasRecursivas {

    // Algoritmo recursivo de Búsqueda Binaria por nombre
    public static int busquedaBinariaPorNombre(HistorialClinico[] A, String nombre, int i, int j) {
        int medio;
        if (i > j) return -1; // Caso base: el objeto no existe
        medio = (i + j) / 2;
        int comparacion = A[medio].getNombre().compareToIgnoreCase(nombre);
        if (comparacion < 0) return busquedaBinariaPorNombre(A, nombre, medio + 1, j);
        else if (comparacion > 0) return busquedaBinariaPorNombre(A, nombre, i, medio - 1);
        else return medio; // Caso base: se encuentra la clave
    }
}
