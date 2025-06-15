package Procesos;

import EstructurasListas.Pacientes;  // Asegúrate de tener la clase Pacientes
import EstructurasListas.ListaEnlazada;
import EstructurasListas.Nodo;
import Vista.Forma02;  // Esta es la vista para el formulario de pacientes
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProcesosForma02 {

    // Método para limpiar las entradas en el formulario
    public static void LimpiarEntradas(Forma02 f2) {
        f2.txtdni.setText("");
        f2.txtnombres.setText("");
        f2.txtsintoma.setText("");
        f2.txttelefono.setText("");
        f2.txtpeso.setText("");
        f2.txttalla.setText("");
        f2.txtpresion.setText("");
        f2.txttemperatura.setText("");
        f2.txtMotivo.setText("");
        f2.txtAlergias.setText("");
        f2.txtdni.requestFocus();
    }

    // Método para leer los datos de un paciente desde el formulario
    public static Pacientes LeerPacientes(Forma02 f2) {
        Pacientes paciente = new Pacientes();

        // Leer y validar el DNI
        String dni = f2.txtdni.getText();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(f2, "El campo DNI no puede estar vacío");
            return null;
        }
        paciente.setDni(dni);

        // Leer y validar los nombres
        String nombres = f2.txtnombres.getText();
        if (nombres.isEmpty()) {
            JOptionPane.showMessageDialog(f2, "El campo Nombres no puede estar vacío");
            return null;
        }
        paciente.setNombres(nombres);

        // Leer y validar el síntoma
        String sintoma = f2.txtsintoma.getText();
        if (sintoma.isEmpty()) {
            JOptionPane.showMessageDialog(f2, "El campo Síntoma no puede estar vacío");
            return null;
        }
        paciente.setSintoma(sintoma);

        // Leer y validar el teléfono
        String telefonoStr = f2.txttelefono.getText();
        if (telefonoStr.isEmpty()) {
            JOptionPane.showMessageDialog(f2, "El campo Teléfono no puede estar vacío");
            return null;
        }
        try {
            long telefono = Long.parseLong(telefonoStr);
            paciente.setTelefono(telefono);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(f2, "El campo Teléfono debe contener solo números");
            return null;
        }

        // Leer y validar la temperatura
        String temperaturaStr = f2.txttemperatura.getText();
        if (temperaturaStr.isEmpty()) {
            JOptionPane.showMessageDialog(f2, "El campo Temperatura no puede estar vacío");
            return null;
        }
        try {
            paciente.setTemperatura(Double.parseDouble(temperaturaStr));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(f2, "El campo Temperatura debe ser numérico");
            return null;
        }

        // Leer y validar la presión
        String presionStr = f2.txtpresion.getText();
        if (presionStr.isEmpty()) {
            JOptionPane.showMessageDialog(f2, "El campo Presión no puede estar vacío");
            return null;
        }
        try {
            paciente.setPresion(Double.parseDouble(presionStr));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(f2, "El campo Presión debe ser numérico");
            return null;
        }

        // Leer y validar el peso
        String pesoStr = f2.txtpeso.getText();
        if (pesoStr.isEmpty()) {
            JOptionPane.showMessageDialog(f2, "El campo Peso no puede estar vacío");
            return null;
        }
        try {
            paciente.setPeso(Double.parseDouble(pesoStr));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(f2, "El campo Peso debe ser numérico");
            return null;
        }

        // Leer y validar la talla
        String tallaStr = f2.txttalla.getText();
        if (tallaStr.isEmpty()) {
            JOptionPane.showMessageDialog(f2, "El campo Talla no puede estar vacío");
            return null;
        }
        try {
            paciente.setTalla(Double.parseDouble(tallaStr));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(f2, "El campo Talla debe ser numérico");
            return null;
        }

        // Leer y validar las alergias y el motivo de consulta
        paciente.setAlergias(f2.txtAlergias.getText());
        paciente.setMotivoConsulta(f2.txtMotivo.getText());

        return paciente;
    }

    // Método para mostrar los datos de la lista en la tabla
    public static void MostrarDatos(Forma02 f2, ListaEnlazada lista) {
        String[] titulos = {"Nro", "DNI", "Nombres", "Síntoma", "Teléfono", "Temperatura", "Presión", "Peso", "Talla", "Alergias", "Motivo de Consulta"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        f2.tblDatos.setModel(modelo);

        Nodo aux = lista.getIni();
        int numeracion = 0;

        while (aux != null) {
            numeracion++;
            modelo.addRow(aux.getPaciente().Registro(numeracion));
            aux = aux.getSig();
        }
    }

    // Método para mostrar los datos de un nodo específico en el formulario
    public static void MostrarDatosNodo(Nodo actual, Forma02 f2) {
        if (actual != null && actual.getPaciente() != null) {
            f2.txtdni.setText(actual.getPaciente().getDni());
            f2.txtnombres.setText(actual.getPaciente().getNombres());
            f2.txttelefono.setText(Long.toString(actual.getPaciente().getTelefono()));
            f2.txttemperatura.setText(Double.toString(actual.getPaciente().getTemperatura()));
            f2.txtpresion.setText(Double.toString(actual.getPaciente().getPresion()));
            f2.txtpeso.setText(Double.toString(actual.getPaciente().getPeso()));
            f2.txttalla.setText(Double.toString(actual.getPaciente().getTalla()));
            f2.txtAlergias.setText(actual.getPaciente().getAlergias());
            f2.txtMotivo.setText(actual.getPaciente().getMotivoConsulta());
            f2.txtsintoma.setText(actual.getPaciente().getSintoma());
        } else {
            JOptionPane.showMessageDialog(f2, "ERROR: El nodo actual o el objeto paciente es null.");
        }
    }
}
