package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public class InstruccionDIV extends Instruccion {

    private long n;
    private int direccionamiento;

    public InstruccionDIV(long n, int direccionamiento) {
        this.n = n;
        this.direccionamiento = direccionamiento;
    }

    @Override
    public void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros) {
        switch (direccionamiento) {
            case Instruccion.DIRECTO:
                long v = registros.getRegistro(n).getValor();
                if(v == 0){
                    acum.setValor(0);
                }else{
                    acum.setValor(acum.getValor() / v);
                }
                break;
            case Instruccion.INDIRECTO:
                long reg = registros.getRegistro(n).getValor();
                long valor = registros.getRegistro(reg).getValor();
                if(valor == 0){
                    acum.setValor(0);
                }else{
                    acum.setValor(acum.getValor() / valor);
                }
                break;
            default:
                if(n == 0){
                    acum.setValor(0);
                }else{
                    acum.setValor(acum.getValor() / n);
                }
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
