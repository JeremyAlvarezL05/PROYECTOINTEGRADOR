package Procesos;

import EstructurasListas.Doctor;
import EstructurasListas.ListaEnlazada;
import EstructurasListas.Nodo;
import ModeloArreglo.ArregloHistorialesClinicos;
import ModeloArreglo.HistorialClinico;
import Vista.Forma08;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProcesosForma08 {

    private static ArregloHistorialesClinicos historialesClinicos = new ArregloHistorialesClinicos();

    public static void sincronizarDatosPorDNI(Forma08 vista, String dni, ListaEnlazada listaDoctores) {
        HistorialClinico historial = historialesClinicos.buscarHistorialPorDni(dni);
        if (historial != null) {
            vista.txtDNI.setText(historial.getDni());

            // Buscar doctor por ID en la lista de doctores
            Nodo nodoDoctor = listaDoctores.BuscarDoctorPorId(historial.getDoctor());
            if (nodoDoctor != null) {
                Doctor doctor = nodoDoctor.getDoctor();
                vista.txtDoctor.setText(doctor.getIdDoctor());
                Object[] fila = {
                    doctor.getIdDoctor(),
                    doctor.getNombres(),
                    doctor.getApellidos(),
                    doctor.getTelefono(),
                    doctor.getCorreo(),
                    doctor.getCertificaciones(),
                    doctor.getUniversidad()
                };
                DefaultTableModel model = (DefaultTableModel) vista.tblDatos.getModel();
                model.setRowCount(0); // Limpiar la tabla
                model.addRow(fila);
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró el doctor para el ID proporcionado.");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontró el historial clínico para el DNI proporcionado.");
        }
    }

    // Método para filtrar doctores por código
    public static Object[][] filtrarDoctoresPorCodigo(ListaEnlazada listaDoctores, String codigo) {
        Nodo actual = listaDoctores.getIni();
        Object[][] datos = new Object[listaDoctores.getSize()][9];
        int i = 0;
        while (actual != null) {
            Doctor doctor = actual.getDoctor();
            if (doctor != null && doctor.getIdDoctor().equalsIgnoreCase(codigo)) {
                datos[i][0] = i + 1;
                datos[i][1] = doctor.getDni();
                datos[i][2] = doctor.getIdDoctor();
                datos[i][3] = doctor.getNombres();
                datos[i][4] = doctor.getApellidos();
                datos[i][5] = doctor.getTelefono();
                datos[i][6] = doctor.getCorreo();
                datos[i][7] = doctor.getCertificaciones();
                datos[i][8] = doctor.getUniversidad();
                i++;
            }
            actual = actual.getSig();
        }
        return datos;
    }
}
