package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public class InstruccionRES extends Instruccion {

    private long n;
    private int direccionamiento;

    public InstruccionRES(long n, int direccionamiento) {
        this.n = n;
        this.direccionamiento = direccionamiento;
    }

    @Override
    public void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros) {
        switch (direccionamiento) {
            case Instruccion.DIRECTO:
                acum.setValor(Math.max(0, acum.getValor() - registros.getRegistro(n).getValor()));
                break;
            case Instruccion.INDIRECTO:
                long v = registros.getRegistro(n).getValor();
                acum.setValor(Math.max(0, acum.getValor() - registros.getRegistro(v).getValor()));
                break;
            default:
                acum.setValor(Math.max(0, acum.getValor() - n));
        }
        cp.setValor(cp.getValor() + 1);
    }

    @Override
    public String mostrar() {
        switch (direccionamiento) {
            case Instruccion.DIRECTO:
                return "SUM R(" + n + ")";
            case Instruccion.INDIRECTO:
                return "SUM R(R(" + n + "))";
            default:
                return "SUM " + n;
        }
    }
}
