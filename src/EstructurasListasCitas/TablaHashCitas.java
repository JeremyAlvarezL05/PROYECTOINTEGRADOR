package EstructurasListasCitas;

import java.util.HashMap;

public class TablaHashCitas {
    private HashMap<String, Nodo> tablaHash;

    public TablaHashCitas(ListaEnlazada lista) {
        tablaHash = new HashMap<>();
        Nodo aux = lista.getIni();
        while (aux != null) {
            tablaHash.put(aux.cita.getIdCita(), aux);
            aux = aux.sig;
        }
    }

    public Nodo buscarPorId(String idCita) {
        return tablaHash.get(idCita);
    }
}
