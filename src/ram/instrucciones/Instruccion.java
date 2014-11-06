package ram.instrucciones;

import ram.Registro;
import ram.Registros;

/**
 * @author Parisi Germán
 */
public abstract class Instruccion {
    public static final int INMEDIATO = 1;
    public static final int DIRECTO = 2;
    public static final int INDIRECTO = 3;
    public abstract void ejecutar(StringBuilder entrada, StringBuilder salida, Registro cl, Registro ce, Registro cp, Registro acum, Registros registros);
    public abstract String mostrar();
}
