package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public class InstruccionIMP extends Instruccion {

    private long n;
    private int direccionamiento;

    public InstruccionIMP(long n, int direccionamiento) {
        this.n = n;
        this.direccionamiento = direccionamiento;
    }

    @Override
    public void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros) {
        switch (direccionamiento) {
            case Instruccion.INDIRECTO:
                long v = registros.getRegistro(n).getValor();
                salida.append(registros.getRegistro(v).getValor());
                break;
            case Instruccion.DIRECTO:
                long va = registros.getRegistro(n).getValor();
                salida.append(va);
                break;
            default:
                salida.append(n);
        }
        cp.setValor(cp.getValor() + 1);
        ce.setValor(ce.getValor() + 1);
        salida.append("|");
    }

    @Override
    public String mostrar() {
        switch (direccionamiento) {
            case Instruccion.INDIRECTO:
                return "IMP R(R(" + n + "))";
            case Instruccion.DIRECTO:
                return "IMP R(" + n + ")";
            default:
                return "IMP " + n;
        }
    }
}
