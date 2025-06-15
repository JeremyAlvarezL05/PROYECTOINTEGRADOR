package Procesos;

import ModeloArreglo.HistorialClinico;
import ModeloArreglo.ArregloHistorialesClinicos;
import EstructurasListas.ListaEnlazada;
import EstructurasListas.Nodo;
import Vista.Forma01;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ProcesosForma01 {
    private static Stack<HistorialClinico> pilaOperaciones = new Stack<>();
    private static Queue<HistorialClinico> colaCitas = new LinkedList<>();
    private static ArbolHistoriales arbolHistoriales = new ArbolHistoriales();

    public static void registrarHistorial(Forma01 f1, ArregloHistorialesClinicos lista) {
        HistorialClinico historial = leerHistorial(f1);
        pilaOperaciones.push(historial); // Guardar en pila
        lista.agregarHistorial(historial);
        lista.guardarEnArchivo();
        arbolHistoriales.insertar(historial); // Insertar en árbol
        mostrarEnTabla(f1, lista.getLista());
        JOptionPane.showMessageDialog(f1, "Historial registrado.");
    }
    
    public static void limpiarEntradas(Forma01 vista) {
        vista.txtDni.setText("");
        vista.txtNombre.setText("");
        vista.txtTratamiento.setText("");
        vista.cbxDoctor.setSelectedIndex(0); // Seleccionar el primer elemento del ComboBox
        vista.jDateChooser1.setDate(null); // Limpiar el selector de fecha
        vista.txthoracita.setText(""); // Limpiar el campo de hora
        vista.txtDiagnostico.setText(""); // Limpiar el campo de diagnóstico
        vista.txtMotivo1.setText(""); // Limpiar el campo de motivo de consulta
        vista.cbxEspecialidad.setSelectedIndex(0); // Seleccionar la especialidad predeterminada
        vista.txtDni.requestFocus(); // Poner el cursor en el campo de DNI
    }

    // 2. Método para ordenar los historiales clínicos por el nombre del paciente
    public static void ordenarHistorialesPorNombre(Forma01 vista, ArregloHistorialesClinicos lista) {
        HistorialClinico[] historiales = lista.getLista();
        Arrays.sort(historiales, 0, ArregloHistorialesClinicos.getCantHistoriales(),
                Comparator.comparing(HistorialClinico::getNombre, String.CASE_INSENSITIVE_ORDER));

        mostrarEnTabla(vista, historiales); // Actualiza la tabla con los datos ordenados
    }

    // 3. Método para ordenar los historiales clínicos por la especialidad
    public static void ordenarHistorialesPorEspecialidad(Forma01 vista, ArregloHistorialesClinicos lista) {
        HistorialClinico[] historiales = lista.getLista();
        Arrays.sort(historiales, 0, ArregloHistorialesClinicos.getCantHistoriales(),
                Comparator.comparing(HistorialClinico::getEspecialidad, String.CASE_INSENSITIVE_ORDER));

        mostrarEnTabla(vista, historiales); // Actualiza la tabla con los datos ordenados
    }

    // 4. Método para buscar un historial clínico por DNI
public static void buscarPacientePorDni(Forma01 vista, ListaEnlazada listaDoctores, String dni) {
    Nodo nodo = listaDoctores.BuscarDoctor(dni); // Método de ListaEnlazada para buscar un doctor por DNI
    if (nodo != null) {
        vista.txtDni.setText(nodo.getDoctor().getDni());
        vista.txtNombre.setText(nodo.getDoctor().getNombres());
        JOptionPane.showMessageDialog(vista, "Doctor encontrado: " + nodo.getDoctor().getNombres());
    } else {
        JOptionPane.showMessageDialog(vista, "No se encontró un doctor con ese DNI.");
    }
}

    // 5. Método para agregar la fecha seleccionada a la tabla
    public static void agregarFechaATabla(Forma01 vista, Date fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaStr = sdf.format(fecha);

            DefaultTableModel model = (DefaultTableModel) vista.tblDatos.getModel();
            int selectedRow = vista.tblDatos.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt(fechaStr, selectedRow, 7); // Actualizar la columna de fecha
            } else {
                JOptionPane.showMessageDialog(vista, "Por favor, seleccione una fila para agregar la fecha.");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione una fecha válida.");
        }
    }

    // 6. Método para buscar un historial clínico usando búsqueda binaria
    public static void buscarHistorialBinario(Forma01 vista, ArregloHistorialesClinicos lista, String dni) {
        lista.ordenarPorDniRecursivo(); // Ordenar la lista antes de realizar la búsqueda binaria
        HistorialClinico historial = lista.buscarHistorialPorDni(dni);

        if (historial != null) {
            // Mostrar el historial encontrado
            JOptionPane.showMessageDialog(vista, "Historial encontrado:\n" + historial.toString());
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontró un historial clínico con ese DNI.");
        }
    }

    // Método auxiliar para mostrar datos en la tabla
    public static void mostrarEnTabla(Forma01 vista, HistorialClinico[] lista) {
        String[] titulos = {"DNI", "Nombre", "Especialidad", "Tratamiento", "Doctor", "Motivo", "Diagnóstico", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null) {
                modelo.addRow(lista[i].Registro(i + 1));
            }
        }
        vista.tblDatos.setModel(modelo); // Establece el modelo en la tabla
    }

    
       // Método de búsqueda por DNI
    public static int buscarHistorialPorDNI(HistorialClinico[] lista, String dni) {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null && lista[i].getDni().equals(dni)) {
                return i; // Devuelve la posición del historial encontrado
            }
        }
        return -1; // No se encontró el historial
    }

    public static void buscarHistorial(Forma01 f1, ArregloHistorialesClinicos lista, String dni) {
        HistorialClinico resultado = lista.buscarHistorialPorDni(dni);
        if (resultado != null) {
            JOptionPane.showMessageDialog(f1, resultado.toString());
        } else {
            JOptionPane.showMessageDialog(f1, "Historial no encontrado.");
        }
    }

    // Método para eliminar un historial por DNI
    public static boolean eliminarHistorial(Forma01 vista, ArregloHistorialesClinicos lista, String dni) {
        // Buscar la posición del historial clínico por DNI
        int posicion = buscarHistorialPorDNI(lista.getLista(), dni);

        if (posicion != -1) {
            // Eliminar el historial si se encuentra
            lista.eliminarHistorial(posicion);
            lista.guardarEnArchivo();
            mostrarEnTabla(vista, lista.getLista()); // Actualizar la tabla en la vista
            return true; // Eliminación exitosa
        } else {
            return false; // No se encontró el historial
        }
    }
    
    // Método para poblar el ComboBox con los nombres de los doctores
    public static void poblarComboBoxDoctores(Forma01 vista, ListaEnlazada listaDoctores) {
        // Limpiar el ComboBox antes de agregar elementos
        vista.cbxDoctor.removeAllItems();
        
        // Recorrer la lista de doctores
        Nodo nodoActual = listaDoctores.getIni();
        while (nodoActual != null) {
            if (nodoActual.getDoctor() != null) {
                // Agregar nombre completo del doctor al ComboBox
                String nombreCompleto = nodoActual.getDoctor().getNombres() + " " + nodoActual.getDoctor().getApellidos();
                vista.cbxDoctor.addItem(nombreCompleto);
            }
            nodoActual = nodoActual.getSig(); // Avanzar al siguiente nodo
        }
    }

    public static void deshacerOperacion(Forma01 f1, ArregloHistorialesClinicos lista) {
        if (!pilaOperaciones.isEmpty()) {
            HistorialClinico ultimo = pilaOperaciones.pop();
            lista.eliminarHistorialPorDni(ultimo.getDni());
            lista.guardarEnArchivo();
            mostrarEnTabla(f1, lista.getLista());
            JOptionPane.showMessageDialog(f1, "Última operación deshecha.");
        } else {
            JOptionPane.showMessageDialog(f1, "No hay operaciones para deshacer.");
        }
    }

    public static void agregarCita(Forma01 f1) {
        HistorialClinico historial = leerHistorial(f1);
        colaCitas.add(historial);
        JOptionPane.showMessageDialog(f1, "Cita agregada.");
    }

    public static void atenderCita(Forma01 f1) {
        if (!colaCitas.isEmpty()) {
            HistorialClinico siguiente = colaCitas.poll();
            JOptionPane.showMessageDialog(f1, "Atendiendo cita de: " + siguiente.getNombre());
        } else {
            JOptionPane.showMessageDialog(f1, "No hay citas pendientes.");
        }
    }

    public static void buscarHistorialEnArbol(Forma01 f1, String dni) {
        HistorialClinico resultado = arbolHistoriales.buscar(dni);
        if (resultado != null) {
            JOptionPane.showMessageDialog(f1, resultado.toString());
        } else {
            JOptionPane.showMessageDialog(f1, "Historial no encontrado.");
        }
    }

public static HistorialClinico leerHistorial(Forma01 f1) {
    HistorialClinico historial = new HistorialClinico();
    historial.setDni(f1.txtDni.getText());
    historial.setNombre(f1.txtNombre.getText());

    // Verificar si el elemento seleccionado no es null antes de llamar a .toString()
    Object especialidad = f1.cbxEspecialidad.getSelectedItem();
    if (especialidad != null) {
        historial.setEspecialidad(especialidad.toString());
    } else {
        historial.setEspecialidad(""); // Asignar un valor predeterminado
    }

    historial.setTratamiento(f1.txtTratamiento.getText());

    // Verificar si el elemento seleccionado no es null antes de llamar a .toString()
    Object doctor = f1.cbxDoctor.getSelectedItem();
    if (doctor != null) {
        historial.setDoctor(doctor.toString());
    } else {
        historial.setDoctor(""); // Asignar un valor predeterminado
    }

    historial.setMotivoConsulta(f1.txthoracita.getText());
    historial.setDiagnostico(f1.txtDiagnostico.getText());

    Date fecha = f1.jDateChooser1.getDate();
    if (fecha != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        historial.setFecha(sdf.format(fecha));
    } else {
        historial.setFecha(""); // Asignar un valor predeterminado
    }

    return historial;
}

}
