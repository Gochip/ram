package principal;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ram.RAM;
import ram.Registro;
import ram.instrucciones.Instruccion;

/**
 * @author Parisi Germán
 */
public class Controlador {

    private static Controlador yo;
    private VentanaPrincipal ventanaPrincipal;

    private Controlador() {
    }

    public static Controlador getInstancia() {
        if (yo == null) {
            yo = new Controlador();
        }
        return yo;
    }

    public void ejecutarPrograma(String entrada, String programa) {
        RAM ram = new RAM();
        try {
            List<Instruccion> instrucciones = CreacionPrograma.crearPrograma(programa);
            ram.setEntrada(new StringBuilder(entrada));
            ram.ejecutarPrograma(instrucciones, true);
            ventanaPrincipal.setSalida(ram.getSalida().toString());
            ventanaPrincipal.setCP(ram.getCP());
            ventanaPrincipal.setAcumulador(ram.getAcumulador());
            ventanaPrincipal.setRegistrosDatos(ram.getRegistros());
            ventanaPrincipal.finEjecucionPrograma();
//            System.out.println("-------------");
//            System.out.println("CP: " + ram.getCp());
//            System.out.println("Acumulador: " + ram.getAcumulador());
//            System.out.println("Registros");
//            for (Registro r : ram.getRegistros()) {
//                System.out.println(r);
//            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventanaPrincipal, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void iniciar() {
        ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setVisible(true);
    }
}
