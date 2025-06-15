
package Procesos;

import Vista.FrmPrincipal;
import javax.swing.JFrame;

public class PresentarMenu {
    
    public static void MostrarMenu(FrmPrincipal fp){
       fp.setVisible(true);
       fp.setTitle("Sistema de Posta Medica");
       fp.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
            
    
    
}
