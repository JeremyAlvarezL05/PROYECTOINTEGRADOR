package EstructurasListasCitas;

import java.io.Serializable;

public class Nodo implements Serializable {
    public Cita cita;
    public Nodo sig;

    public Nodo(Cita c) {
        this.cita = c;
        this.sig = null;
    }
}
