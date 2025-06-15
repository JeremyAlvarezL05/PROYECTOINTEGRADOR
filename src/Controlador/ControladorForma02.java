package Controlador;

import EstructurasListas.ListaEnlazada;
import EstructurasListas.Nodo;
import EstructurasListas.Pacientes;
import Vista.Forma02;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorForma02 implements ActionListener {

    ListaEnlazada listaPacientes;  // Lista enlazada para almacenar los pacientes
    Forma02 vista;

    public ControladorForma02(Forma02 f2) {
        vista = f2;
        vista.btnAgregarFinal.addActionListener(this);
        vista.btnAgregarInicio.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        vista.btnConsultar.addActionListener(this);
        vista.btnActualizar.addActionListener(this);
        vista.btnOrdenarAsc.addActionListener(this); // Botón para ordenar ascendente
        vista.btnOrdenarDesc.addActionListener(this); // Botón para ordenar descendente

        listaPacientes = new ListaEnlazada();  // Inicialización de la lista enlazada

        // Mostrar datos iniciales en la tabla
        MostrarDatos(listaPacientes);

        // Listener para la selección de pacientes en la tabla
        vista.tblDatos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosDesdeTabla();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Agregar un paciente al inicio de la lista enlazada
        if (e.getSource() == vista.btnAgregarInicio) {
            agregarPacienteInicio();
        }

        // Agregar un paciente al final de la lista enlazada
        if (e.getSource() == vista.btnAgregarFinal) {
            agregarPacienteFinal();
        }

        // Consultar un paciente en la lista enlazada por su DNI
        if (e.getSource() == vista.btnConsultar) {
            String codbuscado = JOptionPane.showInputDialog(vista, "Ingrese el DNI a buscar:");
            Nodo nodoPaciente = listaPacientes.BuscarPaciente(codbuscado);  // Buscar el paciente por DNI
            if (nodoPaciente == null) {
                JOptionPane.showMessageDialog(vista, "ERROR: El DNI " + codbuscado + " no está registrado");
            } else {
                MostrarDatosNodo(nodoPaciente);  // Mostrar los datos del paciente en el formulario
                vista.txtdni.requestFocus();
            }
        }

        // Eliminar un paciente de la lista enlazada por su DNI
        if (e.getSource() == vista.btnEliminar) {
            String dni = JOptionPane.showInputDialog(vista, "Ingrese el DNI del paciente a eliminar:");
            if (dni != null && !dni.isEmpty()) {
                Nodo pacienteAEliminar = listaPacientes.BuscarPaciente(dni);  // Buscar el paciente a eliminar
                if (pacienteAEliminar != null) {
                    listaPacientes.EliminarPacientes(pacienteAEliminar);  // Eliminar el paciente de la lista
                    MostrarDatos(listaPacientes);  // Actualizar la tabla
                    LimpiarEntradas();
                } else {
                    JOptionPane.showMessageDialog(vista, "Paciente no encontrado.");
                }
            }
        }

        // Limpiar entradas del formulario
        if (e.getSource() == vista.btnlimpiar) {
            LimpiarEntradas();
        }

        // Actualizar los datos de un paciente en la lista
        if (e.getSource() == vista.btnActualizar) {
            Nodo nodoPaciente = listaPacientes.BuscarPaciente(vista.txtdni.getText());
            if (nodoPaciente != null) {
                Pacientes pacienteActualizado = LeerPaciente();
                nodoPaciente.setPaciente(pacienteActualizado);  // Actualizar el nodo con los nuevos datos
                MostrarDatos(listaPacientes);
                LimpiarEntradas();
            } else {
                JOptionPane.showMessageDialog(vista, "Primero debe consultar un paciente para actualizar.");
            }
        }

        // Ordenar pacientes por nombre en orden ascendente
        if (e.getSource() == vista.btnOrdenarAsc) {
            listaPacientes.OrdenarPorNombresAscendente();  // Ordenar la lista en orden ascendente
            MostrarDatos(listaPacientes);  // Mostrar los datos ordenados
        }

        // Ordenar pacientes por nombre en orden descendente
        if (e.getSource() == vista.btnOrdenarDesc) {
            listaPacientes.OrdenarPorNombresDescendente();  // Ordenar la lista en orden descendente
            MostrarDatos(listaPacientes);  // Mostrar los datos ordenados
        }
    }

    // Método para agregar un paciente al inicio de la lista enlazada
    private void agregarPacienteInicio() {
        Pacientes paciente = LeerPaciente();
        if (paciente != null) {
            Nodo nuevoNodo = new Nodo(paciente);
            listaPacientes.AgregarNodoInicio(nuevoNodo);  // Insertar nodo al inicio
            MostrarDatos(listaPacientes);
            LimpiarEntradas();
        }
    }

    // Método para agregar un paciente al final de la lista enlazada
    private void agregarPacienteFinal() {
        Pacientes paciente = LeerPaciente();
        if (paciente != null) {
            Nodo nuevoNodo = new Nodo(paciente);
            listaPacientes.AgregarNodoFinal(nuevoNodo);  // Insertar nodo al final
            MostrarDatos(listaPacientes);
            LimpiarEntradas();
        }
    }

    // Método para mostrar los datos de todos los pacientes en la tabla
    private void MostrarDatos(ListaEnlazada lista) {
        String[] titulos = {"Nro", "DNI", "Nombres", "Síntoma", "Teléfono", "Temperatura", "Presión", "Peso", "Talla", "Alergias", "Motivo de Consulta"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        vista.tblDatos.setModel(modelo);

        Nodo aux = lista.getIni();
        int numeracion = 0;

        while (aux != null) {
            numeracion++;
            modelo.addRow(aux.getPaciente().Registro(numeracion));  // Obtener los datos de un nodo
            aux = aux.getSig();  // Recorrer la lista enlazada
        }
    }

    // Método para mostrar los datos de un paciente en el formulario
    private void MostrarDatosNodo(Nodo nodo) {
        Pacientes paciente = nodo.getPaciente();
        vista.txtdni.setText(paciente.getDni());
        vista.txtnombres.setText(paciente.getNombres());
        vista.txtsintoma.setText(paciente.getSintoma());
        vista.txttelefono.setText(Long.toString(paciente.getTelefono()));
        vista.txttemperatura.setText(Double.toString(paciente.getTemperatura()));
        vista.txtpresion.setText(Double.toString(paciente.getPresion()));
        vista.txtpeso.setText(Double.toString(paciente.getPeso()));
        vista.txttalla.setText(Double.toString(paciente.getTalla()));
        vista.txtAlergias.setText(paciente.getAlergias());
        vista.txtMotivo.setText(paciente.getMotivoConsulta());
    }

    // Método para cargar los datos de un paciente desde la tabla seleccionada
    private void cargarDatosDesdeTabla() {
        int filaSeleccionada = vista.tblDatos.getSelectedRow();
        if (filaSeleccionada != -1) {
            String dni = (String) vista.tblDatos.getValueAt(filaSeleccionada, 1);
            Nodo nodoPaciente = listaPacientes.BuscarPaciente(dni);
            if (nodoPaciente != null) {
                MostrarDatosNodo(nodoPaciente);
            }
        }
    }

    // Leer los datos de un paciente desde el formulario
    private Pacientes LeerPaciente() {
        String dni = vista.txtdni.getText();
        String nombres = vista.txtnombres.getText();
        String sintoma = vista.txtsintoma.getText();
        String telefonoStr = vista.txttelefono.getText();

        long telefono;
        try {
            telefono = Long.parseLong(telefonoStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El número de teléfono debe ser numérico.");
            return null;
        }

        String temperaturaStr = vista.txttemperatura.getText();
        double temperatura = Double.parseDouble(temperaturaStr);

        String presionStr = vista.txtpresion.getText();
        double presion = Double.parseDouble(presionStr);

        String pesoStr = vista.txtpeso.getText();
        double peso = Double.parseDouble(pesoStr);

        String tallaStr = vista.txttalla.getText();
        double talla = Double.parseDouble(tallaStr);

        String alergias = vista.txtAlergias.getText();
        String motivoConsulta = vista.txtMotivo.getText();

        Pacientes paciente = new Pacientes();
        paciente.setDni(dni);
        paciente.setNombres(nombres);
        paciente.setSintoma(sintoma);
        paciente.setTelefono(telefono);
        paciente.setTemperatura(temperatura);
        paciente.setPresion(presion);
        paciente.setPeso(peso);
        paciente.setTalla(talla);
        paciente.setAlergias(alergias);
        paciente.setMotivoConsulta(motivoConsulta);

        return paciente;
    }

    // Limpiar los campos del formulario
    private void LimpiarEntradas() {
        vista.txtdni.setText("");
        vista.txtnombres.setText("");
        vista.txtsintoma.setText("");
        vista.txttelefono.setText("");
        vista.txttemperatura.setText("");
        vista.txtpresion.setText("");
        vista.txtpeso.setText("");
        vista.txttalla.setText("");
        vista.txtAlergias.setText("");
        vista.txtMotivo.setText("");
    }
}
