package Procesos;

import EstructurasListasCitas.Cita;
import EstructurasListasCitas.ListaEnlazada;
import EstructurasListasCitas.Nodo;
import Vista.Forma03;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProcesosForma03 {

    // Limpia las entradas del formulario
    public static void LimpiarEntradas(Forma03 f3) {
        f3.txtIdCita.setText("");
        f3.txtHoraCita.setText("");
        f3.txtNombrePaciente.setText("");
        f3.txtdnipaciente.setText("");
        f3.txtNombreDoctor.setText("");
        f3.cbxEstadoCita.setSelectedIndex(0); // Cambiar a cbxEstadoCita
        f3.txtMotivoCita.setText("");
        f3.txtIdCita.requestFocus();
    }

    // Lee los datos de la cita desde el formulario
    public static Cita LeerCita(Forma03 f3) {
        Cita cita = new Cita();
        cita.setIdCita(f3.txtIdCita.getText());
        cita.setHoraCita(f3.txtHoraCita.getText());
        cita.setNombrePaciente(f3.txtNombrePaciente.getText());
        cita.setDnipaciente(f3.txtdnipaciente.getText());
        cita.setNombreDoctor(f3.txtNombreDoctor.getText());
        cita.setEstadoCita(f3.cbxEstadoCita.getSelectedItem().toString()); // Cambiar a cbxEstadoCita
        cita.setMotivoCita(f3.txtMotivoCita.getText());

        return cita;
    }

    // Muestra los datos de todas las citas en la tabla
    public static void MostrarDatos(Forma03 f3, ListaEnlazada lista) {
        String titulos[] = {
            "Nro", 
            "ID Cita", 
            "Hora Cita", 
            "Nombre Paciente", 
            "DNI Paciente", 
            "Nombre Doctor", 
            "Estado Cita", // Cambiar a Estado Cita
            "Motivo Cita"
        };
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        f3.tblDatos.setModel(modelo);

        Nodo aux = lista.getIni();
        int numeracion = 0;
        while (aux != null) {
            numeracion++;
            modelo.addRow(aux.cita.Registro(numeracion));
            aux = aux.sig;
        }
    }

    // Muestra los datos de una cita en el formulario
    public static void MostrarDatosNodo(Nodo actual, Forma03 f3) {
        if (actual != null && actual.cita != null) {
            f3.txtIdCita.setText(actual.cita.getIdCita());
            f3.txtHoraCita.setText(actual.cita.getHoraCita());
            f3.txtNombrePaciente.setText(actual.cita.getNombrePaciente());
            f3.txtdnipaciente.setText(actual.cita.getDnipaciente());
            f3.txtNombreDoctor.setText(actual.cita.getNombreDoctor());
            f3.cbxEstadoCita.setSelectedItem(actual.cita.getEstadoCita()); // Cambiar a cbxEstadoCita
            f3.txtMotivoCita.setText(actual.cita.getMotivoCita());
        } else {
            JOptionPane.showMessageDialog(f3, "ERROR: actual o actual.cita es null.");
        }
    }
}
