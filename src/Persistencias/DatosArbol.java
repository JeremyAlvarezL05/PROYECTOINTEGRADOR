package Persistencias;

import java.io.*;
import Procesos.*;
import Estructura.*;

public class DatosArbol {
    public static void GuardarEnArchivo(ArbolEspecialidad arbol) {
        try {
            FileOutputStream fos = new FileOutputStream("DatosArbolEspecialidad.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arbol);
            oos.close();
        } catch (Exception ex) {
            Mensajes.Texto("ERROR no se puede guardar " + ex);
        }
    } // fin guardar

    public static ArbolEspecialidad RecuperarDeArchivo() {
        ArbolEspecialidad arbol = new ArbolEspecialidad();
        try {
            FileInputStream fis = new FileInputStream("DatosArbolEspecialidad.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arbol = (ArbolEspecialidad) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            Mensajes.Texto("ERROR no se puede recuperar de archivo " + ex);
        }
        return arbol;
    } // fin recuperar
} // fin de la clase
