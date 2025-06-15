package Procesos;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class PresentarFrame {
    public static void Centrar(JInternalFrame frame,JDesktopPane escritorio){
        escritorio.removeAll();        
        escritorio.add(frame);
        frame.setVisible(true);
        Dimension tamanioescritorio = escritorio.getSize();
        Dimension tamanioframe= frame.getSize();
        int x=(tamanioescritorio.width-tamanioframe.width)/2;
        int y=(tamanioescritorio.height-tamanioframe.height)/2;
        frame.setLocation(x, y);
        frame.show();
        escritorio.repaint();
    }            
}
