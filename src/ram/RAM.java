package ram;

import ram.instrucciones.Instruccion;
import java.util.List;

/**
 * @author Parisi Germán
 */
public class RAM {

    /**
     * Cabezal de lectura.
     */
    private Registro cl;
    /**
     * Cabezal de escritura.
     */
    private Registro ce;
    /**
     * Registros de datos.
     */
    private Registros registros;
    /**
     * Registro acumulador.
     */
    private Registro acumulador;
    /**
     * Contador de programa.
     */
    private Registro cp;
    /**
     * Cinta de entrada.
     */
    private StringBuilder entrada;
    /**
     * Cinta de salida.
     */
    private StringBuilder salida;

    public RAM() {
        registros = new Registros();
        acumulador = new Registro(0);
        cp = new Registro(-1);
        cp.setValor(1);
        cl = new Registro(-2);
        ce = new Registro(-3);
        salida = new StringBuilder();
    }

    public void ejecutarPrograma(List<Instruccion> instrucciones, boolean mostrar) {
        if(mostrar){
            System.out.println("Ejecutando programa");
        }
        for (; cp.getValor() <= instrucciones.size();) {
            Instruccion instruccion = instrucciones.get((int) (cp.getValor() - 1));
            if (mostrar) {
                System.out.println(cp.getValor() + ") " + instruccion.mostrar());
            }
            instruccion.ejecutar(entrada, salida, cl, ce, cp, acumulador, registros);
        }
        if(mostrar){
            System.out.println("Fin programa");
        }
    }

    public void setEntrada(StringBuilder entrada) {
        this.entrada = entrada;
    }

    public StringBuilder getSalida() {
        return this.salida;
    }

    public long getCL() {
        return cl.getValor();
    }

    public long getCE() {
        return ce.getValor();
    }

    public long getAcumulador() {
        return acumulador.getValor();
    }

    public long getCP() {
        return cp.getValor();
    }

    public List<Registro> getRegistros() {
        return this.registros.getRegistros();
    }
}
