package Busquedas;

import ModeloArreglo.*;

public class BusquedasArreglos {
    public static int secuencial(String elemento, ArregloHistorialesClinicos lista) {
        if (elemento == null || elemento.trim().isEmpty()) {
            return -1; // Retorna -1 si el elemento es nulo o vacío
        }
        int n = ArregloHistorialesClinicos.getCantHistoriales();
        for (int i = 0; i < n; i++) {
            if (elemento.equals(lista.recuperarHistorial(i).getDni())) {
                return i;
            }
        }
        return -1; // indica que el objeto buscado no existe
    }

    public static int busquedaBinariaPorDni(HistorialClinico[] vector, String dniBuscado) {
        if (dniBuscado == null || dniBuscado.trim().isEmpty()) {
            return -1; // Retorna -1 si el dniBuscado es nulo o vacío
        }
        int n = ArregloHistorialesClinicos.getCantHistoriales();
        int inferior = 0, superior = n - 1;
        while (inferior <= superior) {
            int centro = (inferior + superior) / 2;
            if (dniBuscado.equalsIgnoreCase(vector[centro].getDni())) {
                return centro;
            } else {
                if (dniBuscado.compareToIgnoreCase(vector[centro].getDni()) <= 0)
                    superior = centro - 1;
                else
                    inferior = centro + 1;
            }
        }
        return -1; // indica que no existe
    }
}
