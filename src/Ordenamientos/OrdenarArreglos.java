package Ordenamientos;

// libreria
import ModeloArreglo.ArregloHistorialesClinicos;
import ModeloArreglo.HistorialClinico;
import Procesos.Mensajes;

public class OrdenarArreglos {

    public static HistorialClinico[] ordenarPorDni(HistorialClinico a[]) {
        for (int i = 1; i < ArregloHistorialesClinicos.getCantHistoriales(); i++) {
            HistorialClinico value = a[i];
            int j = i;
            while (j > 0 && a[j - 1].getDni().compareTo(value.getDni()) > 0) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = value;
        }
        return a;
    }

    public static HistorialClinico[] ordenarPorEspecialidadASC(HistorialClinico a[]) {
        for (int i = 1; i < ArregloHistorialesClinicos.getCantHistoriales(); i++) {
            HistorialClinico value = a[i];
            int j = i;
            while (j > 0 && a[j - 1].getEspecialidad().compareTo(value.getEspecialidad()) > 0) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = value;
        }
        return a;
    }

    public static HistorialClinico[] ordenarPorEspecialidadDESC(HistorialClinico a[]) {
        for (int i = 1; i < ArregloHistorialesClinicos.getCantHistoriales(); i++) {
            HistorialClinico value = a[i];
            int j = i;
            while (j > 0 && a[j - 1].getEspecialidad().compareTo(value.getEspecialidad()) < 0) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = value;
        }
        return a;
    }

     public static void quicksort(HistorialClinico[] A, int izq, int der) {
    HistorialClinico pivote = A[izq];
    int i = izq;
    int j = der;
    HistorialClinico aux;
    while (i < j) {
        while (A[i].getNombre().compareToIgnoreCase(pivote.getNombre()) <= 0 && i < j) i++;
        while (A[j].getNombre().compareToIgnoreCase(pivote.getNombre()) > 0) j--;
        if (i < j) {
            aux = A[i];
            A[i] = A[j];
            A[j] = aux;
        }
    }
    A[izq] = A[j];
    A[j] = pivote;
    if (izq < j - 1) quicksort(A, izq, j - 1);
    if (j + 1 < der) quicksort(A, j + 1, der);
}

    public static void ordenarPorNombreASC(HistorialClinico A[], int izq, int der) {
        HistorialClinico pivote = A[izq];
        int i = izq;
        int j = der;
        HistorialClinico aux;
        while (i < j) {
            while (A[i].getNombre().compareTo(pivote.getNombre()) <= 0 && i < j) i++;
            while (A[j].getNombre().compareTo(pivote.getNombre()) > 0) j--;
            if (i < j) {
                aux = A[i];
                A[i] = A[j];
                A[j] = aux;
            }
        }
        A[izq] = A[j];
        A[j] = pivote;
        if (izq < j - 1) ordenarPorNombreASC(A, izq, j - 1);
        if (j + 1 < der) ordenarPorNombreASC(A, j + 1, der);
    }

    
    
}
