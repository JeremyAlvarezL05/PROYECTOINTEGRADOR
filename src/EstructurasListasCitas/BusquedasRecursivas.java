package EstructurasListasCitas;

public class BusquedasRecursivas {

    public static Nodo buscarPacientePorDNI(Nodo nodo, String dni) {
        if (nodo == null) return null;
        if (dni.equals(nodo.cita.getDnipaciente())) return nodo;
        return buscarPacientePorDNI(nodo.sig, dni);
    }
}
