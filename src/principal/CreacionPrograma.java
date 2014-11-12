package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ram.instrucciones.Instruccion;
import ram.instrucciones.InstruccionALM;
import ram.instrucciones.InstruccionCAR;
import ram.instrucciones.InstruccionDIV;
import ram.instrucciones.InstruccionIMP;
import ram.instrucciones.InstruccionLEE;
import ram.instrucciones.InstruccionMUL;
import ram.instrucciones.InstruccionRES;
import ram.instrucciones.InstruccionSAL;
import ram.instrucciones.InstruccionSUM;
import ram.instrucciones.InstruccionSXI;
import ram.instrucciones.InstruccionSXM;

/**
 * @author Parisi Germán
 */
public class CreacionPrograma {

    public static final String CAR = "car";
    public static final String ALM = "alm";
    public static final String LEE = "lee";
    public static final String IMP = "imp";
    public static final String SAL = "sal";
    public static final String SUM = "sum";
    public static final String SXI = "sxi";
    public static final String SXM = "sxm";
    public static final String RES = "res";
    public static final String MUL = "mul";
    public static final String DIV = "div";
    public static final String FIN = "fin";

    public static List<Instruccion> crearPrograma(String programa) throws Exception {
        List<Instruccion> instrucciones = new ArrayList<>();
        int k = 1;
        Scanner sc = new Scanner(programa);
        try {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.equalsIgnoreCase(FIN)) {
                    break;
                }
                String partes[] = linea.split(" ");
                String comando = partes[0].toLowerCase();
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
                    case IMP:
                        i = new InstruccionIMP(getParametro(param), getTipoDireccionamiento(param));
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
                    case SXI:
                        i = new InstruccionSXI(getParametro(param));
                        instrucciones.add(i);
                        break;
                    case SXM:
                        i = new InstruccionSXM(getParametro(param));
                        instrucciones.add(i);
                        break;
                    case RES:
                        i = new InstruccionRES(getParametro(param), getTipoDireccionamiento(param));
                        instrucciones.add(i);
                        break;
                    case MUL:
                        i = new InstruccionMUL(getParametro(param), getTipoDireccionamiento(param));
                        instrucciones.add(i);
                        break;
                    case DIV:
                        i = new InstruccionDIV(getParametro(param), getTipoDireccionamiento(param));
                        instrucciones.add(i);
                        break;
                }
                k++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new Exception("Falló al leer la línea número " + k);
        }
        return instrucciones;
    }

    public static int getTipoDireccionamiento(String param) {
        if (param.length() >= 4 && param.charAt(0) == 'R' && param.charAt(1) == '(' && param.charAt(2) == 'R' && param.charAt(3) == '(') {
            return Instruccion.INDIRECTO;
        } else if (param.length() >= 2 && param.charAt(0) == 'R' && param.charAt(1) == '(') {
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
        int r = 0;
        try {
            r = Integer.parseInt(sb.toString());
            if(r < 0){
                r = (int) sb.toString().charAt(0);
            }
        } catch (NumberFormatException e) {
            r = (int) sb.toString().charAt(0);
        }
        return r;
    }
}
