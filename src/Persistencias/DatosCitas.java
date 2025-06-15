package Persistencias;

import EstructurasListasCitas.*;
import java.io.*;

public class DatosCitas {

    public static ListaEnlazada RecuperaDeArchivo() {
        ListaEnlazada lista = new ListaEnlazada();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("Citas.dat"))) {
            lista = (ListaEnlazada) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al recuperar la lista de citas: " + e.getMessage());
        }
        return lista;
    }

    public static void GuardarEnArchivo(ListaEnlazada lista) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("Citas.dat"))) {
            salida.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Error al guardar la lista de citas: " + e.getMessage());
        }
    }

   
    }

