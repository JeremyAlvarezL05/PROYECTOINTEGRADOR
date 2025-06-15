package ModeloArreglo;

import java.io.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ArregloHistorialesClinicos implements Serializable {
    private HistorialClinico[] lista;
    private static int cantHistoriales = 0;

    public ArregloHistorialesClinicos() {
        lista = new HistorialClinico[50];
    }

    public void agregarHistorial(HistorialClinico historial) {
        if (cantHistoriales < lista.length) {
            lista[cantHistoriales] = historial;
            cantHistoriales++;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más historiales. Lista llena.");
        }
    }

    public HistorialClinico recuperarHistorial(int posicion) {
        if (posicion >= 0 && posicion < cantHistoriales) {
            return lista[posicion];
        }
        return null;
    }

    public void eliminarHistorial(int posicion) {
        if (posicion >= 0 && posicion < cantHistoriales) {
            for (int j = posicion; j < cantHistoriales - 1; j++) {
                lista[j] = lista[j + 1];
            }
            lista[cantHistoriales - 1] = null; // Limpia la última posición
            cantHistoriales--;
        } else {
            JOptionPane.showMessageDialog(null, "Posición inválida.");
        }
    }

    public void actualizarHistorial(int posicion, HistorialClinico actualizado) {
        if (posicion >= 0 && posicion < cantHistoriales) {
            lista[posicion] = actualizado;
        } else {
            JOptionPane.showMessageDialog(null, "Posición inválida.");
        }
    }

    public void guardarEnArchivo() {
        try (FileOutputStream fos = new FileOutputStream("ListaHistorialesClinicos.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lista);
            oos.writeInt(cantHistoriales); // Guarda también el número de historiales
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede guardar.." + ex);
        }
    }

    public void recuperarDeArchivo() {
        try (FileInputStream fis = new FileInputStream("ListaHistorialesClinicos.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            lista = (HistorialClinico[]) ois.readObject();
            cantHistoriales = ois.readInt(); // Recupera también el número de historiales
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR no se puede recuperar.." + ex);
        }
    }

    public void actualizarCantidadHistoriales() {
        cantHistoriales = 0;
        for (HistorialClinico historial : lista) {
            if (historial != null) cantHistoriales++;
        }
    }

    public void enviarDatos(JComboBox<String> combo) {
        combo.removeAllItems();
        for (int i = 0; i < cantHistoriales; i++) {
            combo.addItem(lista[i].getNombre());
        }
    }

    // Método de búsqueda binaria por DNI
    public int busquedaBinariaPorDni(String dni, int i, int j) {
        if (i > j) return -1;
        int medio = (i + j) / 2;
        int comparacion = lista[medio].getDni().compareToIgnoreCase(dni);
        if (comparacion < 0) return busquedaBinariaPorDni(dni, medio + 1, j);
        else if (comparacion > 0) return busquedaBinariaPorDni(dni, i, medio - 1);
        else return medio;
    }

    // Método para ordenar la lista por DNI usando el algoritmo de burbuja recursivo
    public void ordenarPorDniRecursivo() {
        ordenarPorDniRecursivo(lista.length - 1);
    }
    
    public int eliminarHistorialPorDni(String dni) {
        for (int i = 0; i < cantHistoriales; i++) {
            if (lista[i] != null && lista[i].getDni().equalsIgnoreCase(dni)) {
                for (int j = i; j < cantHistoriales - 1; j++) {
                    lista[j] = lista[j + 1];
                }
                lista[cantHistoriales - 1] = null;
                cantHistoriales--;
                return i;
            }
        }
        return -1; // Historial no encontrado
    }


    private void ordenarPorDniRecursivo(int n) {
        if (n == 0) return;

        for (int i = 0; i < n; i++) {
            if (lista[i] != null && lista[i + 1] != null) {
                if (lista[i].getDni().compareToIgnoreCase(lista[i + 1].getDni()) > 0) {
                    HistorialClinico temp = lista[i];
                    lista[i] = lista[i + 1];
                    lista[i + 1] = temp;
                }
            }
        }
        ordenarPorDniRecursivo(n - 1);
    }

    // Método para buscar historial por DNI
    public HistorialClinico buscarHistorialPorDni(String dni) {
        ordenarPorDniRecursivo(); // Asegura que la lista esté ordenada antes de la búsqueda
        int posicion = busquedaBinariaPorDni(dni, 0, cantHistoriales - 1);
        return (posicion == -1) ? null : recuperarHistorial(posicion);
    }

    // Nuevo método getHistorial
    public HistorialClinico getHistorial(String dni) {
        for (int i = 0; i < cantHistoriales; i++) {
            if (lista[i] != null && lista[i].getDni().equalsIgnoreCase(dni)) {
                return lista[i];
            }
        }
        return null;
    }

    // Getter y Setter
    public HistorialClinico[] getLista() {
        return lista;
    }

    public void setLista(HistorialClinico[] lista) {
        this.lista = lista;
    }

    public static int getCantHistoriales() {
        return cantHistoriales;
    }

    public static void setCantHistoriales(int cantHistoriales) {
        ArregloHistorialesClinicos.cantHistoriales = cantHistoriales;
    }
}
