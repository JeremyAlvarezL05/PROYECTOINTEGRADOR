package Controlador;

import Procesos.PresentarMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import EstructurasListas.ListaEnlazada;
import Procesos.PresentarFrame;

public class ControladorMenu implements ActionListener {
    FrmPrincipal vista;
    ListaEnlazada listaDoctores;  // Añadir la lista de doctores

    public ControladorMenu(FrmPrincipal fp) {
        vista = fp;
        listaDoctores = new ListaEnlazada(); // Inicializar la lista de doctores
        PresentarMenu.MostrarMenu(vista);

        vista.registropacientes.addActionListener(this);
        vista.historiaclinica.addActionListener(this);
        vista.Especialidad.addActionListener(this);
        vista.registrodoctor.addActionListener(this);
        vista.Busquedas.addActionListener(this);  // Añadir el listener para el menú de búsquedas
        vista.jcitas.addActionListener(this);
        vista.atenciones.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.Especialidad) {
            Forma06 f6 = new Forma06();
            ControlForma06 cf6 = new ControlForma06(f6);
            f6.setTitle("Especialidades");
            PresentarFrame.Centrar(f6, vista.dpnEscritorio);
        }

        if (e.getSource() == vista.registropacientes) {
            Forma02 f2 = new Forma02();
            ControladorForma02 cf2 = new ControladorForma02(f2);
            f2.setTitle("Registro de Pacientes");
            PresentarFrame.Centrar(f2, vista.dpnEscritorio);
        }

        if (e.getSource() == vista.historiaclinica) {
            Forma01 f1 = new Forma01();
            ControladorForma01 cf1 = new ControladorForma01(f1);
            PresentarFrame.Centrar(f1, vista.dpnEscritorio);
        }

        if (e.getSource() == vista.registrodoctor) {
            
            Forma04 f4 = new Forma04();
            ControladorForma04 cf4 = new ControladorForma04(f4);
            f4.setTitle("REGISTRO DEL DOCTOR");
            PresentarFrame.Centrar(f4, vista.dpnEscritorio);
        }
        if (e.getSource() == vista.atenciones) {
            System.out.println("ijobuob");
            Forma07 f7 = new Forma07();
            ControladorForma07 cf7 = new ControladorForma07(f7);
            f7.setTitle("ATENCION DEL DOCTOR");
            PresentarFrame.Centrar(f7, vista.dpnEscritorio);
        } 

        if (e.getSource() == vista.Busquedas) {
            Forma08 f8 = new Forma08();
            ControladorForma08 cf8 = new ControladorForma08(f8, listaDoctores);  // Pasar la lista de doctores
            f8.setTitle("Búsquedas de Doctores");
            PresentarFrame.Centrar(f8, vista.dpnEscritorio);
        }
        if (e.getSource() == vista.jcitas) {
            Forma03 f3 = new Forma03();
            ControladorForma03 cf3 = new ControladorForma03(f3); // Asumir que ControladorForma03 se encarga de la vista Forma03
            f3.setTitle("Búsquedas de Citas");
            PresentarFrame.Centrar(f3, vista.dpnEscritorio);
            f3.setVisible(true);
        }
    }
}
