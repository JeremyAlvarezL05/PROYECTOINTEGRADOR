
package Procesos;

import EstructurasListas.Doctor;
import EstructurasListas.ListaEnlazada;
import EstructurasListas.Nodo;
import Vista.Forma04;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProcesosForma04 {
    public static void LimpiarEntradas(Forma04 f4) {
        f4.txtdni.setText("");
        f4.txtnombres.setText("");
        f4.txtapellidos.setText("");
        f4.txttelefono.setText("");
        f4.txtIdDoctor.setText("");
        f4.txtCorreo.setText(""); 
        f4.txtCorreo.requestFocus();
        f4.txtUniversidad.setText("");
        f4.txtCertificaciones.setText("");
    }
    public static Object[][] FiltrarDoctoresPorCodigo(ListaEnlazada lista, String doctorId) {
        Nodo actual = lista.getIni();
        Object[][] datosFiltrados = new Object[lista.getSize()][9]; // Asumiendo un tamaño máximo
        int index = 0;

        while (actual != null) {
            Doctor doc = actual.getDoctor();
            if (doc != null && doc.getIdDoctor().equals(doctorId)) {
                datosFiltrados[index] = doc.registro(index + 1);
                index++;
            }
            actual = actual.getSig();
        }

        // Redimensionar el array para eliminar filas nulas
        Object[][] result = new Object[index][9];
        System.arraycopy(datosFiltrados, 0, result, 0, index);

        return result;
    }

    public static Doctor LeerDoctor(Forma04 f4) {
        Doctor doctor = new Doctor();

        // Leer y validar el DNI
        String dni = f4.txtdni.getText();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(f4, "El campo DNI no puede estar vacío");
            return null;
        }
        doctor.setDni(dni);

        // Leer y validar el Id del Doctor
        String iddoctor = f4.txtIdDoctor.getText();
        if (iddoctor.isEmpty()) {
            JOptionPane.showMessageDialog(f4, "El campo Id del Doctor no puede estar vacío");
            return null;
        }
        doctor.setIdDoctor(iddoctor);

        // Leer y validar los nombres
        String nombres = f4.txtnombres.getText();
        if (nombres.isEmpty()) {
            JOptionPane.showMessageDialog(f4, "El campo Nombres no puede estar vacío");
            return null;
        }
        doctor.setNombres(nombres);

        // Leer y validar los apellidos
        String apellidos = f4.txtapellidos.getText();
        if (apellidos.isEmpty()) {
            JOptionPane.showMessageDialog(f4, "El campo Apellidos no puede estar vacío");
            return null;
        }
        doctor.setApellidos(apellidos);

        // Leer y validar el teléfono
        String telefonoStr = f4.txttelefono.getText();
        if (telefonoStr.isEmpty()) {
            JOptionPane.showMessageDialog(f4, "El campo Teléfono no puede estar vacío");
            return null;
        }
        try {
            long telefono = Long.parseLong(telefonoStr);
            doctor.setTelefono(telefono);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(f4, "El campo Teléfono debe contener solo números");
            return null;
        }

        // Leer y validar el correo
        String correo = f4.txtCorreo.getText();
        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(f4, "El campo Correo no puede estar vacío");
            return null;
        }
        doctor.setCorreo(correo);

        // Leer y validar la universidad
        String universidad = f4.txtUniversidad.getText();
        if (universidad.isEmpty()) {
            JOptionPane.showMessageDialog(f4, "El campo Universidad no puede estar vacío");
            return null;
        }
        doctor.setUniversidad(universidad);

        // Leer y validar las certificaciones
        String certificaciones = f4.txtCertificaciones.getText();
        if (certificaciones.isEmpty()) {
            JOptionPane.showMessageDialog(f4, "El campo Certificaciones no puede estar vacío");
            return null;
        }
        doctor.setCertificaciones(certificaciones);

        return doctor;
    }

    public static void MostrarDatos(Forma04 f4, ListaEnlazada lista) {
        String[] titulos = {"Nro", "DNI", "IdDoctor", "Nombres", "Apellidos", "Teléfono", "Correo", "Certificaciones", "Universidad"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        f4.tblDatos.setModel(modelo);

        Nodo aux = lista.getIni();
        int numeracion = 0;

        while (aux != null) {
            numeracion++;
            modelo.addRow(aux.getDoctor().registro(numeracion));
            aux = aux.getSig();
        }
    }

    public static void MostrarDatosNodo(Nodo actual, Forma04 f4) {
        if (actual != null && actual.getDoctor() != null) {
            f4.txtdni.setText(actual.getDoctor().getDni());
            f4.txtIdDoctor.setText(actual.getDoctor().getIdDoctor());
            f4.txtnombres.setText(actual.getDoctor().getNombres());
            f4.txtapellidos.setText(actual.getDoctor().getApellidos());
            f4.txttelefono.setText(Long.toString(actual.getDoctor().getTelefono()));
            f4.txtCorreo.setText(actual.getDoctor().getCorreo());
            f4.txtCertificaciones.setText(actual.getDoctor().getCertificaciones());
            f4.txtUniversidad.setText(actual.getDoctor().getUniversidad());
        } else {
            JOptionPane.showMessageDialog(f4, "ERROR: El nodo actual o el objeto doctor es null.");
        }
    }
}
