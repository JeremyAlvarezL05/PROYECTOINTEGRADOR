
package Procesos;

import ModeloArreglo.ArregloHistorialesClinicos;
import ModeloArreglo.HistorialClinico;
import Vista.Forma07;

import javax.swing.table.DefaultTableModel;

public class ProcesosForma07 {
public static void mostrarEnTabla(Forma07 f7, HistorialClinico[] a) {
    // Definir los títulos de las columnas que quieres mostrar
    String[] titulosMostrar = {"DOCTOR", "FECHA"};
    
    // Crear el modelo de tabla con los títulos filtrados
    DefaultTableModel mt = new DefaultTableModel(null, titulosMostrar);
    
    // Establecer el modelo en la tabla
    f7.tblAtencion.setModel(mt);
    
    // Recorrer el arreglo de historiales clínicos y agregar solo las columnas requeridas
    for (int i = 0; i < ArregloHistorialesClinicos.getCantHistoriales(); i++) {
        Object[] fila = {
            a[i].getDoctor(),
            a[i].getFecha()
        };
        mt.addRow(fila);
    }
}

    
}


