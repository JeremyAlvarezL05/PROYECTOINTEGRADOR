
package EstructurasListas;

import java.io.Serializable;

public class ListaEnlazada implements Serializable {
    private Nodo ini;
    private Nodo fin;
    private int tamaño;

    public ListaEnlazada() {
        ini = null;
        fin = null;
        tamaño = 0;
    }

    // Método para agregar nodo al inicio de la lista
    public void AgregarNodoInicio(Nodo nuevo) {
        if (ini == null && fin == null) { // La lista está vacía
            ini = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSig(ini); // Conectar el nuevo nodo con el antiguo inicio
            ini = nuevo; // El nuevo nodo es el nuevo inicio
        }
        tamaño++;
    }

    // Método para agregar nodo al final de la lista
    public void AgregarNodoFinal(Nodo nuevo) {
        if (ini == null && fin == null) { // La lista está vacía
            ini = nuevo;
            fin = nuevo;
        } else {
            fin.setSig(nuevo); // Conectar el nodo al final
            fin = nuevo; // Actualizar el puntero al final
        }
        tamaño++;
    }

    // Método para buscar un paciente por DNI
    public Nodo BuscarPaciente(String dni) {
        Nodo actual = ini;
        while (actual != null) {
            if (actual.getPaciente() != null && actual.getPaciente().getDni().equalsIgnoreCase(dni)) {
                return actual;
            }
            actual = actual.getSig();
        }
        return null; // Si no se encuentra el paciente
    }

    // Método para eliminar un paciente
    public void EliminarPacientes(Nodo actual) {
        if (actual == null) return;

        if (ini == actual) {
            ini = actual.getSig(); // Eliminar el nodo del inicio
            if (ini == null) fin = null; // Si la lista queda vacía
        } else {
            Nodo anterior = ini;
            while (anterior.getSig() != actual && anterior.getSig() != null) {
                anterior = anterior.getSig();
            }
            if (anterior.getSig() == actual) {
                anterior.setSig(actual.getSig()); // Eliminar el nodo
                if (anterior.getSig() == null) fin = anterior; // Actualizar el fin si era el último nodo
            }
        }
        tamaño--;
    }

    // Método para buscar un doctor por DNI
    public Nodo BuscarDoctor(String codbuscado) {
        Nodo aux = ini;
        while (aux != null && (aux.getDoctor() == null || !codbuscado.equalsIgnoreCase(aux.getDoctor().getDni()))) {
            aux = aux.getSig();
        }
        return aux;
    }

    // Método para buscar un doctor por ID
    public Nodo BuscarDoctorPorId(String idbuscado) {
        Nodo aux = ini;
        while (aux != null && (aux.getDoctor() == null || !idbuscado.equalsIgnoreCase(aux.getDoctor().getIdDoctor()))) {
            aux = aux.getSig();
        }
        return aux;
    }

    // Método para eliminar un doctor
    public void EliminarDoctor(Nodo actual) {
        if (actual == null) return;

        if (ini == actual) {
            ini = actual.getSig();
            if (ini == null) fin = null; // Si la lista queda vacía
        } else {
            Nodo anterior = ini;
            while (anterior.getSig() != actual && anterior.getSig() != null) {
                anterior = anterior.getSig();
            }
            if (anterior.getSig() == actual) {
                anterior.setSig(actual.getSig());
                if (anterior.getSig() == null) fin = anterior;
            }
        }
        tamaño--;
    }

    // Algoritmo para ordenar la lista por nombres en orden ascendente (Bubble Sort)
    public void OrdenarPorNombresAscendente() {
        if (ini == null) return; // Si la lista está vacía no hay nada que ordenar

        boolean huboCambio;
        Nodo actual;
        Nodo siguiente;
        Pacientes temp;

        do {
            actual = ini;
            huboCambio = false;
            while (actual != null && actual.getSig() != null) {
                siguiente = actual.getSig();
                if (actual.getPaciente().getNombres().compareToIgnoreCase(siguiente.getPaciente().getNombres()) > 0) {
                    // Intercambiar los datos de los pacientes
                    temp = actual.getPaciente();
                    actual.setPaciente(siguiente.getPaciente());
                    siguiente.setPaciente(temp);
                    huboCambio = true;
                }
                actual = siguiente;
            }
        } while (huboCambio);
    }

    // Algoritmo para ordenar la lista por nombres en orden descendente (Bubble Sort)
    public void OrdenarPorNombresDescendente() {
        if (ini == null) return; // Si la lista está vacía no hay nada que ordenar

        boolean huboCambio;
        Nodo actual;
        Nodo siguiente;
        Pacientes temp;

        do {
            actual = ini;
            huboCambio = false;
            while (actual != null && actual.getSig() != null) {
                siguiente = actual.getSig();
                if (actual.getPaciente().getNombres().compareToIgnoreCase(siguiente.getPaciente().getNombres()) < 0) {
                    // Intercambiar los datos de los pacientes
                    temp = actual.getPaciente();
                    actual.setPaciente(siguiente.getPaciente());
                    siguiente.setPaciente(temp);
                    huboCambio = true;
                }
                actual = siguiente;
            }
        } while (huboCambio);
    }

    // Obtener el primer nodo de la lista
    public Nodo getIni() {
        return ini;
    }

    public void setIni(Nodo ini) {
        this.ini = ini;
    }

    // Obtener el último nodo de la lista
    public Nodo getFin() {
        return fin;
    }

    public void setFin(Nodo fin) {
        this.fin = fin;
    }

    // Obtener el tamaño de la lista
    public int getSize() {
        return tamaño;
    }
}
