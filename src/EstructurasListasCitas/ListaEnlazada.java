package EstructurasListasCitas;
import java.io.Serializable;

public class ListaEnlazada implements Serializable {
    private Nodo ini;
    private Nodo fin;

    public ListaEnlazada() {
        ini = null;
        fin = null;
    }

    // Método para agregar un nodo al inicio de la lista
    public void AgregarNodoInicio(Nodo nuevo) {
        if (ini == null && fin == null) {
            ini = nuevo;
            fin = nuevo;
        } else {
            nuevo.sig = ini;
            ini = nuevo;
        }
    }

    // Método para agregar un nodo al final de la lista
    public void AgregarNodoFinal(Nodo nuevo) {
        if (ini == null && fin == null) {
            ini = nuevo;
            fin = nuevo;
        } else {
            fin.sig = nuevo;
            fin = nuevo;
        }
        fin.sig = null;
    }

    // Método para buscar un nodo por ID de cita
    public Nodo Buscaridcita(String codbuscado) {
        Nodo aux = ini;
        while (aux != null && (aux.cita == null || !codbuscado.equalsIgnoreCase(aux.cita.getIdCita()))) {
            aux = aux.sig;
        }
        return aux;
    }

    // Método para eliminar un nodo por DNI de paciente
    public void EliminarDNIpaciente(Nodo actual) {
        if (ini == null || actual == null) {
            return; // Si la lista está vacía o el nodo a eliminar es nulo, no hacer nada
        }
        
        if (ini == actual) { // Si el nodo a eliminar es el primero
            ini = actual.sig;
            if (ini == null) {
                fin = null; // Si la lista queda vacía, actualizar el fin también
            }
            return;
        }
        
        Nodo anterior = ini;
        while (anterior.sig != null && anterior.sig != actual) {
            anterior = anterior.sig;
        }
        
        if (anterior.sig == actual) { // Si se encuentra el nodo a eliminar
            anterior.sig = actual.sig;
            if (anterior.sig == null) {
                fin = anterior; // Si se elimina el último nodo, actualizar el fin
            }
        }
    }

    // Métodos getter y setter para ini y fin
    public Nodo getIni() {
        return ini;
    }

    public void setIni(Nodo ini) {
        this.ini = ini;
    }

    public Nodo getFin() {
        return fin;
    }

    public void setFin(Nodo fin) {
        this.fin = fin;
    }
}
