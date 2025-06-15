package Controlador;

// Librerías
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import Estructura.*;
import Modelo.*;
import Persistencias.DatosArbol;
import Procesos.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class ControlForma06 implements ActionListener {
    Forma06 vista;
    DefaultTableModel modtabla;
    ArbolEspecialidad arbol;
    NodoEspecialidad actual;

    public ControlForma06(Forma06 f6) {
        vista = f6;
        vista.btnNuevo.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
        vista.btnActualizar.addActionListener(this);
        vista.setVisible(true);
        vista.setTitle("Registro de especialidades (Árbol Binario...)");
        ProcesosForma06.Estado_Botones(false, vista);
        modtabla = (DefaultTableModel) vista.tblDatos.getModel();
        arbol = DatosArbol.RecuperarDeArchivo();
        arbol.Listar_InOrder(arbol.getRaiz(), modtabla);
        cargarComboBox(); // Carga valores en el JComboBox
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            String especialidad = vista.txtEspecialidad.getText().trim();
            if (especialidad.isEmpty()) {
                Mensajes.Texto("No se ha ingresado ninguna especialidad.");
                return;
            }
            Object[] Registro = {especialidad};
            Especialidad espe = new Especialidad(Registro);
            arbol.setRaiz(arbol.Agregar(arbol.getRaiz(), espe));
            ProcesosForma06.LimpiarEntradas(vista);
            DatosArbol.GuardarEnArchivo(arbol);
            ProcesosForma06.LimpiarTabla(modtabla);
            arbol.Listar_InOrder(arbol.getRaiz(), modtabla);
            cargarComboBox(); // Actualiza el JComboBox después de agregar
            ProcesosForma06.Estado_Botones(false, vista);
        }
        if (e.getSource() == vista.btnNuevo) {
            ProcesosForma06.LimpiarEntradas(vista);
            ProcesosForma06.Estado_Botones(true, vista);
        }
        if (e.getSource() == vista.btnCancelar) {
            ProcesosForma06.LimpiarEntradas(vista);
            ProcesosForma06.Estado_Botones(false, vista);
        }
        if (e.getSource() == vista.btnBuscar) {
            String texto = Mensajes.LeerInfo("Ingrese especialidad a buscar...");
            actual = arbol.BuscarEspe(texto);
            if (actual == null) {
                Mensajes.Texto(texto + " no existe en el árbol.");
                vista.txtEspecialidad.setText("");
                vista.btnActualizar.setEnabled(false);
                vista.txtEspecialidad.setEnabled(false);
            } else {
                vista.txtEspecialidad.setText(actual.getElemento().getEspe());
                vista.txtEspecialidad.requestFocus();
                vista.btnActualizar.setEnabled(true);
                vista.txtEspecialidad.setEnabled(true);
                vista.btnEliminar.setEnabled(true); // Habilita el botón Eliminar al encontrar una especialidad
            }
        } // Fin buscar
        if (e.getSource() == vista.btnEliminar) {
            String especialidad = vista.txtEspecialidad.getText().trim();
            if (especialidad.isEmpty()) {
                Mensajes.Texto("No hay una especialidad seleccionada para eliminar.");
                return;
            }
            int resp = Mensajes.Respuesta("Confirmar Eliminación", "¿Desea eliminar la especialidad seleccionada?");
            if (resp == 0) {
                arbol.setRaiz(arbol.Eliminar(arbol.getRaiz(), especialidad));
                DatosArbol.GuardarEnArchivo(arbol);
                ProcesosForma06.LimpiarEntradas(vista);
                ProcesosForma06.LimpiarTabla(modtabla);
                arbol.Listar_InOrder(arbol.getRaiz(), modtabla);
                cargarComboBox(); // Actualiza el JComboBox después de eliminar
                vista.btnEliminar.setEnabled(false); // Desactiva el botón Eliminar después de eliminar
            }
        } // Fin eliminar
        if (e.getSource() == vista.btnActualizar) {
            String especialidad = vista.txtEspecialidad.getText().trim();
            if (especialidad.isEmpty()) {
                Mensajes.Texto("No se ha ingresado ninguna especialidad.");
                return;
            }
            if (actual == null) {
                Mensajes.Texto("No se ha seleccionado ninguna especialidad para actualizar.");
                return;
            }
            actual.getElemento().setEspe(especialidad);
            DatosArbol.GuardarEnArchivo(arbol);
            ProcesosForma06.LimpiarEntradas(vista);
            ProcesosForma06.LimpiarTabla(modtabla);
            arbol.Listar_InOrder(arbol.getRaiz(), modtabla);
            cargarComboBox(); // Actualiza el JComboBox después de actualizar
            ProcesosForma06.Estado_Botones(true, vista);
            vista.btnEliminar.setEnabled(false); // Desactiva el botón Eliminar después de actualizar
        } // Fin actualizar
    } // Fin de action

    private void cargarComboBox() {
        vista.cbxEspecialidad.removeAllItems();
        for (int i = 0; i < modtabla.getRowCount(); i++) {
            String especialidad = (String) modtabla.getValueAt(i, 0);
            vista.cbxEspecialidad.addItem(especialidad);
        }
    }
} // Fin de la clase