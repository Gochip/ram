package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public class InstruccionSAL extends Instruccion {

    private long m;

    public InstruccionSAL(long m) {
        this.m = m;
    }

    @Override
    public void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros) {
        cp.setValor(m);
    }

    @Override
    public String mostrar() {
        return "SAL " + m;
    }
}
