package Persistencias;

import EstructurasListas.*;
import Procesos.*;
import java.io.*;

public class DatosPacientes {  
    public static void GuardarEnArchivo(ListaEnlazada Lista) {
        try {
            FileOutputStream fos = new FileOutputStream("Pacientesregistro.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Lista);
            oos.close();
        } catch (Exception ex) {
            Mensajes.MostrarTexto("ERROR no se puede guardar Lista Enlazada.." + ex);
        }
    }

    public static ListaEnlazada RecuperaDeArchivo() {
        ListaEnlazada Lista = new ListaEnlazada();
        try {
            FileInputStream fis = new FileInputStream("Pacientesregistro.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Lista = (ListaEnlazada) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            Mensajes.MostrarTexto("ERROR no se puede recuperar Lista Enlazada.." + ex);
        }
        return Lista;
    }

    public static Nodo BuscarPacientePorDni(String dni) {
        ListaEnlazada lista = RecuperaDeArchivo();
        Nodo actual = lista.getIni();
        while (actual != null) {
            if (actual.getPaciente() != null && actual.getPaciente().getDni().equals(dni)) {
                return actual;
            }
            actual = actual.getSig();
        }
        return null;
    }
}
