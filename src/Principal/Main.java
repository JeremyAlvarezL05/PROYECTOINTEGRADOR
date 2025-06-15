package Principal;

import Controlador.ControladorMenu;
import Vista.FrmPrincipal;

public class Main {
    public static void main(String[] args) {
        FrmPrincipal vista = new FrmPrincipal();
        ControladorMenu controlador = new ControladorMenu(vista);
        vista.setVisible(true);
    }
}
