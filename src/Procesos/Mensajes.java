package Procesos;
import javax.swing.JOptionPane;
public class Mensajes {
    
  public static void MostrarTexto(String texto){
      JOptionPane.showMessageDialog(null, texto);
  } 
  
  public static String LeerTexto(String msj){
    return JOptionPane.showInputDialog(msj);
  }
  
  public static int Respuesta(String titulo,String texto){
      return JOptionPane.showConfirmDialog(null,
              texto, titulo,JOptionPane.YES_NO_OPTION);
  }

  public static void Texto(String mensaje){
      JOptionPane.showMessageDialog(null,mensaje);
  }    
  public static String LeerInfo(String mensaje){
      return JOptionPane.showInputDialog(mensaje);
  }
}//fin class



