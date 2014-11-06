package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public class InstruccionALM extends Instruccion {

    private long n;
    private int direccionamiento;

    public InstruccionALM(long n, int direccionamiento) {
        this.n = n;
        this.direccionamiento = direccionamiento;
    }

    @Override
    public void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros) {
        switch (direccionamiento) {
            case Instruccion.INDIRECTO:
                long v = registros.getRegistro(n).getValor();
                registros.setRegistro(v, acum.getValor());
                break;
            default:
                registros.setRegistro(n, acum.getValor());
        }
        cp.setValor(cp.getValor() + 1);
    }

    @Override
    public String mostrar() {
        switch (direccionamiento) {
            case Instruccion.INDIRECTO:
                return "ALM R(R(" + n + "))";
            default:
                return "ALM R(" + n + ")";
        }
    }
}
