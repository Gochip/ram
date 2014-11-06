package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public class InstruccionSI extends Instruccion{

    private long n;
    public InstruccionSI(long n){
        this.n = n;
    }
    @Override
    public void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros) {
        
    }

    @Override
    public String mostrar() {
        return "";
    }

}
