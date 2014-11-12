package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public class InstruccionLEE extends Instruccion {

    private long n;
    private int direccionamiento;

    public InstruccionLEE(long n, int direccionamiento) {
        this.n = n;
        this.direccionamiento = direccionamiento;
    }

    @Override
    public void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros) {
        char c = '\000';
        if (cl.getValor() >= entrada.length()) {
            System.err.println("ERROR, se acabó la entrada");
        } else {
            c = entrada.charAt((int) cl.getValor());
        }
        switch (direccionamiento) {
            case Instruccion.INDIRECTO:
                long v = registros.getRegistro(n).getValor();
                registros.setRegistro(v, c);
                break;
            default:
                registros.setRegistro(n, c);
        }
        cp.setValor(cp.getValor() + 1);
        cl.setValor(cl.getValor() + 1);
    }

    @Override
    public String mostrar() {
        switch (direccionamiento) {
            case Instruccion.INDIRECTO:
                return "LEE R(R(" + n + "))";
            default:
                return "LEE R(" + n + ")";
        }
    }
}
