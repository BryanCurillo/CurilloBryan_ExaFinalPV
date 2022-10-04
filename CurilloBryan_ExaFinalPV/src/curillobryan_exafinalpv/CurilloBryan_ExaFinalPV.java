/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package curillobryan_exafinalpv;
import view.viewPantallaPrincipal;
import controller.controllerPantallaPrincipal;


/**
 *
 * @author Bryan
 */
public class CurilloBryan_ExaFinalPV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        viewPantallaPrincipal vistaPP = new viewPantallaPrincipal();
        controllerPantallaPrincipal controlPP = new controllerPantallaPrincipal(vistaPP);
        controlPP.inicialControl();
    }

}
