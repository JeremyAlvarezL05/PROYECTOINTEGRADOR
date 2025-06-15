/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencias;

import EstructurasListas.ListaEnlazada;
import java.io.*;

public class DatosDoctor {
    private static final String FILE_NAME = "Doctorregistro.bin";

    // Método para guardar la lista en un archivo
    public static void guardarEnArchivo(ListaEnlazada lista) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lista);
        } catch (IOException ex) {
            System.err.println("ERROR: No se puede guardar la lista enlazada. " + ex.getMessage());
        }
    }

    // Método para recuperar la lista desde un archivo
    public static ListaEnlazada recuperarDeArchivo() {
        ListaEnlazada lista = new ListaEnlazada();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    guardarEnArchivo(lista); // Guardar una lista vacía
                }
            } catch (IOException e) {
                System.err.println("ERROR: No se puede crear el archivo. " + e.getMessage());
            }
        } else {
            try (FileInputStream fis = new FileInputStream(FILE_NAME);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                lista = (ListaEnlazada) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("ERROR: No se puede recuperar la lista enlazada. " + ex.getMessage());
            }
        }
        return lista;
    }

    // Método para asegurar la creación del archivo
    private static void asegurarArchivo() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    guardarEnArchivo(new ListaEnlazada());
                }
            } catch (IOException e) {
                System.err.println("ERROR: No se puede crear el archivo. " + e.getMessage());
            }
        }
    }

    // Método para inicializar los datos (asegura que el archivo exista)
    public static void inicializarDatos() {
        asegurarArchivo();
    }

    // Método para recuperar la lista de doctores
    public static ListaEnlazada recuperarListaDoctores() {
        inicializarDatos();
        return recuperarDeArchivo();
    }
}
