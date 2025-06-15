package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import EstructurasListasCitas.*;
import Vista.*;
import Procesos.*;
import Persistencias.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControladorForma03 implements ActionListener {

    private ListaEnlazada Lista;
    private Forma03 vista;
    private Nodo actual;

    public ControladorForma03(Forma03 f3) {
        vista = f3;

        // Añadir listeners a los botones
        vista.btnBusquedaRecursiva.addActionListener(this);
        vista.btnAgregarFinal.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnOrdenar.addActionListener(this);
        vista.btnlimpiar.addActionListener(this);
        vista.btnordenarnombres.addActionListener(this);
        vista.btntablahash.addActionListener(this);
        vista.btnActualizar.addActionListener(this);

        // Añadir listener para la selección de filas en la tabla
        vista.tblDatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = vista.tblDatos.getSelectedRow();
                    if (selectedRow != -1) {
                        String idCita = (String) vista.tblDatos.getValueAt(selectedRow, 1); // Asumiendo que ID Cita está en la columna 1
                        actual = Lista.Buscaridcita(idCita);
                    }
                }
            }
        });

        // Recuperar la lista desde el archivo
        Lista = DatosCitas.RecuperaDeArchivo();

        // Mostrar los datos en la vista
        ProcesosForma03.MostrarDatos(vista, Lista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregarFinal) {
            Nodo nuevo = new Nodo(ProcesosForma03.LeerCita(vista));
            Lista.AgregarNodoFinal(nuevo);

            DatosCitas.GuardarEnArchivo(Lista);
            Lista = DatosCitas.RecuperaDeArchivo(); // Actualizar Lista

            ProcesosForma03.MostrarDatos(vista, Lista);
            ProcesosForma03.LimpiarEntradas(vista);
        }

        if (e.getSource() == vista.btnBusquedaRecursiva) {
            String dniBuscado = Mensajes.LeerTexto("Ingrese el DNI a buscar:");
            Nodo actualBuscado = BusquedasRecursivas.buscarPacientePorDNI(Lista.getIni(), dniBuscado);
            if (actualBuscado == null) {
                Mensajes.MostrarTexto("ERROR: DNI " + dniBuscado + " no está registrado");
            } else {
                actual = actualBuscado;
                Cita citaEncontrada = actual.cita;
                String mensaje = "Datos de la cita:\n"
                                + "ID Cita: " + citaEncontrada.getIdCita() + "\n"
                                + "Hora Cita: " + citaEncontrada.getHoraCita() + "\n"
                                + "Nombre Paciente: " + citaEncontrada.getNombrePaciente() + "\n"
                                + "DNI Paciente: " + citaEncontrada.getDnipaciente() + "\n"
                                + "Nombre Doctor: " + citaEncontrada.getNombreDoctor() + "\n"
                                + "Estado Cita: " + citaEncontrada.getEstadoCita() + "\n" // Cambiar a Estado Cita
                                + "Motivo Cita: " + citaEncontrada.getMotivoCita();
                Mensajes.MostrarTexto(mensaje);
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            if (actual != null) {
                int respuesta = Mensajes.Respuesta("Confirmar!!!", "Desea eliminar " + actual.cita.getIdCita() + " - " + actual.cita.getNombrePaciente() + "?");
                if (respuesta == 0) { // Si=0
                    Lista.EliminarDNIpaciente(actual);

                    DatosCitas.GuardarEnArchivo(Lista);
                    ProcesosForma03.MostrarDatos(vista, Lista);
                    ProcesosForma03.LimpiarEntradas(vista);
                    actual = null; // Reiniciar el nodo actual después de eliminar
                } else {
                    Mensajes.MostrarTexto("Operación cancelada.");
                }
            } else {
                Mensajes.MostrarTexto("No hay nodo seleccionado para eliminar.");
            }
        }

        if (e.getSource() == vista.btnOrdenar) {
            ListaEnlazada listaordenada = Ordenamientos.OrdernarListasCitas.AgruparPorNombreDoctor(Lista);
            ProcesosForma03.MostrarDatos(vista, listaordenada);
        }

        if (e.getSource() == vista.btnlimpiar) {
            ProcesosForma03.LimpiarEntradas(vista);
        }

        if (e.getSource() == vista.btnordenarnombres) {
            Lista = Ordenamientos.OrdernarListasCitas.OrdenarPorNombres(Lista);
            ProcesosForma03.MostrarDatos(vista, Lista);
        }

        if (e.getSource() == vista.btntablahash) {
            String codbuscado = Mensajes.LeerTexto("Ingrese el ID CITA:");
            TablaHashCitas tablaHashCitas = new TablaHashCitas(Lista); // Crear tabla hash con la lista actual
            Nodo nodoEncontrado = tablaHashCitas.buscarPorId(codbuscado);

            if (nodoEncontrado != null) {
                ProcesosForma03.MostrarDatosNodo(nodoEncontrado, vista);
                actual = nodoEncontrado; // Actualizar el nodo actual
            } else {
                Mensajes.MostrarTexto("ERROR: ID " + codbuscado + " no encontrado.");
            }
        }
    }
}
