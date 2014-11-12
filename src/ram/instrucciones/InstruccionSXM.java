package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public class InstruccionSXM extends Instruccion {

    private long m;

    public InstruccionSXM(long m) {
        this.m = m;
    }

    @Override
    public void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros) {
        if(acum.getValor() > 0){
            cp.setValor(m);
        }else{
            cp.setValor(cp.getValor() + 1);
        }
    }

    @Override
    public String mostrar() {
        return "SxM " + m;
    }
}
