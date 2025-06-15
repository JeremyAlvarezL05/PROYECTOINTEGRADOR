package Procesos;
import Vista.*;
import Estructura.*;
import javax.swing.table.DefaultTableModel;
public class ProcesosForma06 {
    public static void Estado_Botones(boolean Estado,Forma06 f){
        f.txtEspecialidad.setEnabled(Estado);
        f.btnNuevo.setEnabled(!Estado);
        f.btnAgregar.setEnabled(Estado);
        f.btnCancelar.setEnabled(Estado);
        f.btnBuscar.setEnabled(!Estado);
        f.btnEliminar.setEnabled(!Estado);
        f.tblDatos.setEnabled(!Estado);                
    }
    
   public static void LimpiarEntradas(Forma06 f){
       f.txtEspecialidad.setText("");
   }
   
   public static void LimpiarTabla(DefaultTableModel modtabla){
       modtabla.setRowCount(0);
   }
  
   
   
}//fin de la clase
