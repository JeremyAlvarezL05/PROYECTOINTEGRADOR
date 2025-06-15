
package Controlador;

import EstructurasListas.ListaEnlazada;
import ModeloArreglo.ArregloHistorialesClinicos;
import Persistencias.DatosDoctor;
import Procesos.ProcesosForma01;
import Procesos.ProcesosForma07;
import Vista.Forma07;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorForma07 implements ActionListener {
    
    Forma07 vista;
    ArregloHistorialesClinicos lista;
    ProcesosForma07 ProcesosForma07;
    ListaEnlazada listaDoctores;
    

    public ControladorForma07(Forma07 forma07) {
        vista = forma07;
        ProcesosForma07 = new ProcesosForma07();
        lista = new ArregloHistorialesClinicos();
        lista.recuperarDeArchivo();
        lista.actualizarCantidadHistoriales();
        listaDoctores = DatosDoctor.recuperarDeArchivo();
        ProcesosForma07.mostrarEnTabla(vista, lista.getLista());
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


