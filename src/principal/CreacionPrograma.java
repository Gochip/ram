package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ram.instrucciones.Instruccion;
import ram.instrucciones.InstruccionALM;
import ram.instrucciones.InstruccionCAR;
import ram.instrucciones.InstruccionLEE;
import ram.instrucciones.InstruccionSAL;
import ram.instrucciones.InstruccionSUM;

/**
 * @author Parisi Germán
 */
public class CreacionPrograma {

    public static final String CAR = "CAR";
    public static final String ALM = "ALM";
    public static final String LEE = "LEE";
    public static final String SAL = "SAL";
    public static final String SUM = "SUM";

    public static List<Instruccion> crearPrograma(String programa) {
        List<Instruccion> instrucciones = new ArrayList<>();
        Scanner sc = new Scanner(programa);
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            String partes[] = linea.split(" ");
            String comando = partes[0];
            String param = partes[1];
            Instruccion i;
            switch (comando) {
                case CAR:
                    i = new InstruccionCAR(getParametro(param), getTipoDireccionamiento(param));
                    instrucciones.add(i);
                    break;
                case ALM:
                    i = new InstruccionALM(getParametro(param), getTipoDireccionamiento(param));
                    instrucciones.add(i);
                    break;
                case LEE:
                    i = new InstruccionLEE(getParametro(param), getTipoDireccionamiento(param));
                    instrucciones.add(i);
                    break;
                case SAL:
                    i = new InstruccionSAL(getParametro(param));
                    instrucciones.add(i);
                    break;
                case SUM:
                    i = new InstruccionSUM(getParametro(param), getTipoDireccionamiento(param));
                    instrucciones.add(i);
                    break;
            }
        }


        return instrucciones;
    }

    public static int getTipoDireccionamiento(String param) {
        if (param.charAt(0) == 'R' && param.charAt(0) == '(' && param.charAt(0) == 'R') {
            return Instruccion.INDIRECTO;
        } else if (param.charAt(0) == 'R') {
            return Instruccion.DIRECTO;
        } else {
            return Instruccion.INMEDIATO;
        }
    }

    public static int getParametro(String param) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < param.length(); i++) {
            if (param.charAt(i) != 'R' && param.charAt(i) != '(' && param.charAt(i) != ')') {
                sb.append(param.charAt(i));
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
