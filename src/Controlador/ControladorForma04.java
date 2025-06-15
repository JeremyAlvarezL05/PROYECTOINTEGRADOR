package Controlador;

import EstructurasListas.Doctor;
import Vista.Forma04;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorForma04 implements ActionListener {

    String[][] matrizDoctores;
    Forma04 vista;
    int contadorDoctores;

    public ControladorForma04(Forma04 f4) {
        vista = f4;
        vista.btnordenarnombres.addActionListener(this);
        vista.btnAgregarFinal.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
        vista.btnAgregarInicio.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        vista.btnConsultarDNI.addActionListener(this);
        vista.btnActualizar.addActionListener(this);

        matrizDoctores = new String[2][8]; // Matriz de 100 filas (doctores) y 8 columnas (atributos)
        contadorDoctores = 0;

        MostrarDatosConArray(vista, matrizDoctores);

        vista.tblDatos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosDesdeTabla();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregarInicio || e.getSource() == vista.btnAgregarFinal || e.getSource() == vista.btnAgregar) {
            agregarDoctor();
        }

        if (e.getSource() == vista.btnConsultarDNI) {
            String codbuscado = JOptionPane.showInputDialog(vista, "Ingrese el DNI a buscar:");
            int indice = BuscarDoctor(codbuscado);
            if (indice == -1) {
                JOptionPane.showMessageDialog(vista, "ERROR: El DNI " + codbuscado + " no está registrado");
            } else {
                MostrarDatosDoctor(indice);
                vista.txtdni.requestFocus();
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            String dni = JOptionPane.showInputDialog(vista, "Ingrese el DNI del doctor a eliminar:");
            if (dni != null && !dni.isEmpty()) {
                int indice = BuscarDoctor(dni);
                if (indice != -1) {
                    EliminarDoctor(indice);
                    MostrarDatosConArray(vista, matrizDoctores);
                    LimpiarEntradas();
                } else {
                    JOptionPane.showMessageDialog(vista, "Doctor no encontrado.");
                }
            }
        }

        if (e.getSource() == vista.btnlimpiar) {
            LimpiarEntradas();
        }

        if (e.getSource() == vista.btnordenarnombres) {
            OrdenarPorNombres();
            MostrarDatosConArray(vista, matrizDoctores);
        }

        if (e.getSource() == vista.btnActualizar) {
            int filaSeleccionada = vista.tblDatos.getSelectedRow();
            if (filaSeleccionada != -1) {
                Doctor doctor = LeerDoctor();
                if (doctor != null) {
                    actualizarDoctor(filaSeleccionada, doctor);
                    MostrarDatosConArray(vista, matrizDoctores);
                    LimpiarEntradas();
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Primero seleccione un doctor de la tabla.");
            }
        }
    }

    // Método para agregar un doctor en la matriz
    private void agregarDoctor() {
        // Verificar si ya se alcanzó el límite de la matriz
        if (contadorDoctores >= matrizDoctores.length) {
            JOptionPane.showMessageDialog(vista, "No se pueden agregar más doctores. Límite de filas alcanzado.");
            return;
        }

        Doctor doctor = LeerDoctor();
        if (doctor != null) {
            if (BuscarDoctor(doctor.getDni()) == -1 && BuscarDoctorPorId(doctor.getIdDoctor()) == -1) {
                matrizDoctores[contadorDoctores] = doctorToArray(doctor, contadorDoctores + 1);
                contadorDoctores++;
                // Ordenar automáticamente después de agregar
                OrdenarPorNombres();
                MostrarDatosConArray(vista, matrizDoctores);
                LimpiarEntradas();
            } else {
                JOptionPane.showMessageDialog(vista, "El doctor con DNI " + doctor.getDni() + " o ID " + doctor.getIdDoctor() + " ya está registrado.");
            }
        }
    }

    // Método para mostrar los datos de la matriz en la tabla
    public void MostrarDatosConArray(Forma04 f4, String[][] arrayDatos) {
        String[] titulos = {"Nro", "DNI", "IdDoctor", "Nombres", "Apellidos", "Teléfono", "Correo", "Certificaciones", "Universidad"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        f4.tblDatos.setModel(modelo);

        for (int i = 0; i < contadorDoctores; i++) {
            modelo.addRow(arrayDatos[i]);
        }
    }

    // Método para convertir un doctor a un array (matriz)
    private String[] doctorToArray(Doctor doctor, int num) {
        return new String[]{
            String.valueOf(num), // Número
            doctor.getDni(),
            doctor.getIdDoctor(),
            doctor.getNombres(),
            doctor.getApellidos(),
            String.valueOf(doctor.getTelefono()),
            doctor.getCorreo(),
            doctor.getCertificaciones(),
            doctor.getUniversidad()
        };
    }

    // Método para buscar un doctor por DNI
    public int BuscarDoctor(String dni) {
        for (int i = 0; i < contadorDoctores; i++) {
            if (matrizDoctores[i][1].equals(dni)) {
                return i;
            }
        }
        return -1;
    }

    // Método para buscar un doctor por ID de doctor
    public int BuscarDoctorPorId(String idDoctor) {
        for (int i = 0; i < contadorDoctores; i++) {
            if (matrizDoctores[i][2].equals(idDoctor)) {
                return i;
            }
        }
        return -1;
    }

    // Método para eliminar un doctor de la matriz
    public void EliminarDoctor(int indice) {
        for (int i = indice; i < contadorDoctores - 1; i++) {
            matrizDoctores[i] = matrizDoctores[i + 1];
        }
        contadorDoctores--;
    }

    // Método para cargar datos desde la tabla
    private void cargarDatosDesdeTabla() {
        int selectedRow = vista.tblDatos.getSelectedRow();
        if (selectedRow != -1) {
            MostrarDatosDoctor(selectedRow);
        }
    }

    // Método para mostrar los datos de un doctor específico en los campos de entrada
    private void MostrarDatosDoctor(int indice) {
        vista.txtdni.setText(matrizDoctores[indice][1]);
        vista.txtIdDoctor.setText(matrizDoctores[indice][2]);
        vista.txtnombres.setText(matrizDoctores[indice][3]);
        vista.txtapellidos.setText(matrizDoctores[indice][4]);
        vista.txttelefono.setText(matrizDoctores[indice][5]);
        vista.txtCorreo.setText(matrizDoctores[indice][6]);
        vista.txtCertificaciones.setText(matrizDoctores[indice][7]);
        vista.txtUniversidad.setText(matrizDoctores[indice][8]);
    }

    // Método para leer los datos de un doctor desde los campos de entrada
    private Doctor LeerDoctor() {
        String dni = vista.txtdni.getText();
        String idDoctor = vista.txtIdDoctor.getText();
        String nombres = vista.txtnombres.getText();
        String apellidos = vista.txtapellidos.getText();

        long telefono;
        try {
            telefono = Long.parseLong(vista.txttelefono.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El número de teléfono debe ser numérico.");
            return null; // Detener el proceso si hay un error en el formato del teléfono
        }

        String correo = vista.txtCorreo.getText();
        String certificaciones = vista.txtCertificaciones.getText();
        String universidad = vista.txtUniversidad.getText();

        Doctor doctor = new Doctor();
        doctor.setDni(dni);
        doctor.setIdDoctor(idDoctor);
        doctor.setNombres(nombres);
        doctor.setApellidos(apellidos);
        doctor.setTelefono(telefono);
        doctor.setCorreo(correo);
        doctor.setCertificaciones(certificaciones);
        doctor.setUniversidad(universidad);

        return doctor;
    }

    // Método para limpiar los campos de entrada
    private void LimpiarEntradas() {
        vista.txtdni.setText("");
        vista.txtIdDoctor.setText("");
        vista.txtnombres.setText("");
        vista.txtapellidos.setText("");
        vista.txttelefono.setText("");
        vista.txtCorreo.setText("");
        vista.txtCertificaciones.setText("");
        vista.txtUniversidad.setText("");
    }

    // Método para actualizar un doctor en la matriz
    private void actualizarDoctor(int fila, Doctor doctor) {
        matrizDoctores[fila] = doctorToArray(doctor, fila + 1);
    }

    // Método para ordenar la matriz por nombres
    private void OrdenarPorNombres() {
        for (int i = 0; i < contadorDoctores - 1; i++) {
            for (int j = i + 1; j < contadorDoctores; j++) {
                if (matrizDoctores[i][3].compareTo(matrizDoctores[j][3]) > 0) {
                    String[] temp = matrizDoctores[i];
                    matrizDoctores[i] = matrizDoctores[j];
                    matrizDoctores[j] = temp;
                }
            }
        }
    }
}
