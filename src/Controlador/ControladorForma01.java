package Controlador;

import Procesos.ProcesosForma01;
import ModeloArreglo.ArregloHistorialesClinicos;
import Persistencias.DatosPacientes;
import Persistencias.DatosDoctor;
import EstructurasListas.ListaEnlazada;
import ModeloArreglo.HistorialClinico;
import Vista.Forma01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ControladorForma01 implements ActionListener {

    private Forma01 vista;
    private ArregloHistorialesClinicos lista;
    private ListaEnlazada listaDoctores;

    public ControladorForma01(Forma01 f1) {
        vista = f1;

        // Asociar eventos a botones
        vista.btnGrabar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnBusquedaBinaria.addActionListener(this);
        vista.btnOrdenar.addActionListener(this);
        vista.btnOrdenarEspecialidad.addActionListener(this);
        vista.btnBuscarID.addActionListener(this);
        vista.btnFecha.addActionListener(this);

        // Inicializar estructuras
        lista = new ArregloHistorialesClinicos();
        lista.recuperarDeArchivo();
        lista.actualizarCantidadHistoriales();
        listaDoctores = DatosDoctor.recuperarDeArchivo();

        // Configurar vista y cargar datos
        ProcesosForma01.mostrarEnTabla(vista, lista.getLista());
        ProcesosForma01.poblarComboBoxDoctores(vista, listaDoctores);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vista.btnGrabar) {
                // Registrar nuevo historial clínico
                HistorialClinico historial = ProcesosForma01.leerHistorial(vista);
                lista.agregarHistorial(historial);
                lista.guardarEnArchivo();
                ProcesosForma01.mostrarEnTabla(vista, lista.getLista());
                JOptionPane.showMessageDialog(vista, "Historial clínico registrado exitosamente.");
            } else if (e.getSource() == vista.btnBuscar) {
                // Buscar historial clínico por DNI
                String dni = JOptionPane.showInputDialog("Ingrese el DNI del historial clínico:");
                if (dni != null && !dni.trim().isEmpty()) {
                    ProcesosForma01.buscarHistorial(vista, lista, dni);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un DNI válido.");
                }
            } else if (e.getSource() == vista.btnEliminar) {
                // Eliminar historial clínico por DNI
                String dni = JOptionPane.showInputDialog("Ingrese el DNI del historial clínico a eliminar:");
                if (dni != null && !dni.trim().isEmpty()) {
                    boolean eliminado = ProcesosForma01.eliminarHistorial(vista, lista, dni);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(vista, "Historial clínico eliminado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se encontró un historial clínico con ese DNI.");
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un DNI válido.");
                }
            } else if (e.getSource() == vista.btnLimpiar) {
                // Limpiar campos de entrada
                ProcesosForma01.limpiarEntradas(vista);
            } else if (e.getSource() == vista.btnOrdenar) {
                // Ordenar historiales clínicos por nombre
                ProcesosForma01.ordenarHistorialesPorNombre(vista, lista);
            } else if (e.getSource() == vista.btnOrdenarEspecialidad) {
                // Ordenar historiales clínicos por especialidad
                ProcesosForma01.ordenarHistorialesPorEspecialidad(vista, lista);
            } else if (e.getSource() == vista.btnBuscarID) {
                // Buscar paciente por DNI
                String dni = JOptionPane.showInputDialog("Ingrese el DNI del paciente:");
                if (dni != null && !dni.trim().isEmpty()) {
                    ProcesosForma01.buscarPacientePorDni(vista, listaDoctores, dni);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un DNI válido.");
                }
            } else if (e.getSource() == vista.btnFecha) {
                // Agregar fecha seleccionada a la tabla
                Date fechaSeleccionada = vista.jDateChooser1.getDate();
                if (fechaSeleccionada != null) {
                    ProcesosForma01.agregarFechaATabla(vista, fechaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, seleccione una fecha.");
                }
            } else if (e.getSource() == vista.btnBusquedaBinaria) {
                // Realizar búsqueda binaria por DNI
                String dni = JOptionPane.showInputDialog("Ingrese el DNI para buscar:");
                if (dni != null && !dni.trim().isEmpty()) {
                    ProcesosForma01.buscarHistorialBinario(vista, lista, dni);
                } else {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese un DNI válido.");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
}
