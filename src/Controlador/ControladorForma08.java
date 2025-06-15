package Controlador;

import Procesos.ProcesosForma08;
import EstructurasListas.ListaEnlazada;
import Vista.Forma08;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorForma08 implements ActionListener {
    private Forma08 vista;
    private ListaEnlazada listaDoctores;

    public ControladorForma08(Forma08 vista, ListaEnlazada listaDoctores) {
        this.vista = vista;
        this.listaDoctores = listaDoctores;
        initListeners();
    }

    private void initListeners() {
        vista.btnBuscar.addActionListener(e -> buscarDatos());
        vista.btnLimpiar.addActionListener(e -> limpiarDatos());
        vista.btnEliminar.addActionListener(e -> eliminarFilaSeleccionada());
        vista.btnEditar.addActionListener(e -> editarFilaSeleccionada());
        vista.txtDNI.addActionListener(e -> sincronizarDatosPorDNI());
    }

    private void buscarDatos() {
        String doctorId = vista.txtDoctor.getText();

        if (doctorId.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El campo Id del Doctor no puede estar vacío.");
            return;
        }

        Object[][] datosFiltrados = obtenerDatosFiltrados(doctorId);
        actualizarTabla(datosFiltrados);
    }

    private Object[][] obtenerDatosFiltrados(String doctorId) {
        return ProcesosForma08.filtrarDoctoresPorCodigo(listaDoctores, doctorId);
    }

    private void actualizarTabla(Object[][] datos) {
        String[] titulos = {"Nro", "DNI", "IdDoctor", "Nombres", "Apellidos", "Teléfono", "Correo", "Certificaciones", "Universidad"};
        DefaultTableModel model = (DefaultTableModel) vista.tblDatos.getModel();
        model.setRowCount(0);

        for (Object[] row : datos) {
            model.addRow(row);
        }
    }

    private void limpiarDatos() {
        vista.txtDNI.setText("");
        vista.txtDoctor.setText("");
        DefaultTableModel model = (DefaultTableModel) vista.tblDatos.getModel();
        model.setRowCount(0);
    }

    private void eliminarFilaSeleccionada() {
        int selectedRow = vista.tblDatos.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) vista.tblDatos.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una fila para eliminar.");
        }
    }

    private void editarFilaSeleccionada() {
        int selectedRow = vista.tblDatos.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) vista.tblDatos.getModel();

            String dni = vista.txtDNI.getText();
            String doctor = vista.txtDoctor.getText();

            model.setValueAt(dni, selectedRow, 1);
            model.setValueAt(doctor, selectedRow, 2);

            JOptionPane.showMessageDialog(vista, "Datos actualizados correctamente.");
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una fila para editar.");
        }
    }

    private void sincronizarDatosPorDNI() {
        String dni = vista.txtDNI.getText();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El campo DNI no puede estar vacío.");
            return;
        }
        ProcesosForma08.sincronizarDatosPorDNI(vista, dni, listaDoctores);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
