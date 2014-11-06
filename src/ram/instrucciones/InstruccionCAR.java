package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public class InstruccionCAR extends Instruccion{

    private long n;
    private int direccionamiento;
    public InstruccionCAR(long n, int direccionamiento){
        this.n = n;
        this.direccionamiento = direccionamiento;
    }
    
    @Override
    public void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros) {
        switch(direccionamiento){
            case Instruccion.DIRECTO:
                acum.setValor(registros.getRegistro(n).getValor());
                break;
            case Instruccion.INDIRECTO:
                long v = registros.getRegistro(n).getValor();
                acum.setValor(registros.getRegistro(v).getValor());
                break;
            default:
                acum.setValor(n);
        }
        cp.setValor(cp.getValor() + 1);
    }

    @Override
    public String mostrar() {
        switch(direccionamiento){
            case Instruccion.DIRECTO:
                return "CAR R(" + n + ")";
            case Instruccion.INDIRECTO:
                return "CAR R(R(" + n + "))";
            default:
                return "CAR " + n;
        }
    }
}
