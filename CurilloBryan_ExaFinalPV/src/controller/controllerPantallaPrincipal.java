/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.viewPantallaPrincipal;
import view.viewRegistroUsu;
import model.modelUsuario;

/**
 *
 * @author Bryan
 */
public class controllerPantallaPrincipal {

    private viewPantallaPrincipal vistaPP;

    public controllerPantallaPrincipal(viewPantallaPrincipal vistaPP) {
        this.vistaPP = vistaPP;
        vistaPP.setLocationRelativeTo(null);
        vistaPP.setVisible(true);
    }

    public void inicialControl() {
        vistaPP.getMIregistroUsu().addActionListener(l -> registroUsuario());
    }

    public void registroUsuario() {
        //Instancio las clases del modelo y la vista        
        viewRegistroUsu vistaRusu = new viewRegistroUsu();
        modelUsuario modelUsu = new modelUsuario();
        vistaPP.getjDesktopPane1().add(vistaRusu);
        vistaRusu.setName("Registro");
        controllerRegistroUsu controlRusu = new controllerRegistroUsu(vistaRusu, modelUsu);
        controlRusu.inicialControl();
    }

}
