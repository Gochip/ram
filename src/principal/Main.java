package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ram.RAM;
import ram.Registro;
import ram.instrucciones.Instruccion;
import ram.instrucciones.InstruccionALM;
import ram.instrucciones.InstruccionCAR;
import ram.instrucciones.InstruccionLEE;
import ram.instrucciones.InstruccionSAL;
import ram.instrucciones.InstruccionSUM;

/**
 *
 * @author Parisi Germán
 */
public class Main {

    private static StringBuilder entrada;

    public static void main(String[] args) {
        RAM ram = new RAM();

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            if (linea.equals("EOF")) {
                break;
            }
            sb.append(linea + "\n");

        }
        entrada = new StringBuilder("AB");
        //List<Instruccion> instrucciones = crearPrograma4();
        String programa = sb.toString();

        List<Instruccion> instrucciones = CreacionPrograma.crearPrograma(programa);
        ram.setEntrada(entrada);
        ram.ejecutarPrograma(instrucciones, true);
        System.out.println("-------------");
        System.out.println("CP: " + ram.getCp());
        System.out.println("Acumulador: " + ram.getAcumulador());
        System.out.println("Registros");
        for (Registro r : ram.getRegistros()) {
            System.out.println(r);
        }
    }

    public static ArrayList<Instruccion> crearPrograma() {
        ArrayList<Instruccion> instrucciones = new ArrayList<Instruccion>();

        // 1) CAR 10
        Instruccion i1 = new InstruccionCAR(10, Instruccion.INMEDIATO);
        instrucciones.add(i1);
        // 2) SUM 5
        Instruccion i2 = new InstruccionSUM(5, Instruccion.INMEDIATO);
        instrucciones.add(i2);
        // 3) SAL 5
        Instruccion i3 = new InstruccionSAL(5);
        instrucciones.add(i3);
        // 4) SUM 5
        Instruccion i4 = new InstruccionSUM(5, Instruccion.INMEDIATO);
        instrucciones.add(i4);
        // 5) SUM 5
        Instruccion i5 = new InstruccionSUM(5, Instruccion.INMEDIATO);
        instrucciones.add(i5);
        // 6) SUM 2
        Instruccion i6 = new InstruccionSUM(2, Instruccion.INMEDIATO);
        instrucciones.add(i6);
        // 7) SUM 1
        Instruccion i7 = new InstruccionSUM(1, Instruccion.INMEDIATO);
        instrucciones.add(i7);
        return instrucciones;
    }

    public static ArrayList<Instruccion> crearPrograma2() {
        ArrayList<Instruccion> instrucciones = new ArrayList<Instruccion>();

        // 1) CAR 10
        Instruccion i1 = new InstruccionCAR(10, Instruccion.INMEDIATO);
        instrucciones.add(i1);
        // 2) SUM 5
        Instruccion i2 = new InstruccionSUM(5, Instruccion.INMEDIATO);
        instrucciones.add(i2);
        // 3) CAR R(1)
        Instruccion i3 = new InstruccionCAR(1, Instruccion.DIRECTO);
        instrucciones.add(i3);
        // 4) CAR 6
        Instruccion i4 = new InstruccionCAR(6, Instruccion.INMEDIATO);
        instrucciones.add(i4);
        // 5) ALM R(2)
        Instruccion i5 = new InstruccionALM(2, Instruccion.DIRECTO);
        instrucciones.add(i5);
        return instrucciones;
    }

    public static ArrayList<Instruccion> crearPrograma3() {
        ArrayList<Instruccion> instrucciones = new ArrayList<Instruccion>();

        // 1) CAR 2
        Instruccion i1 = new InstruccionCAR(2, Instruccion.INMEDIATO);
        instrucciones.add(i1);
        // 2) ALM R(3)
        Instruccion i2 = new InstruccionALM(3, Instruccion.DIRECTO);
        instrucciones.add(i2);
        // 3) CAR 5
        Instruccion i3 = new InstruccionCAR(5, Instruccion.INMEDIATO);
        instrucciones.add(i3);
        // 4) ALM R(2)
        Instruccion i4 = new InstruccionALM(2, Instruccion.DIRECTO);
        instrucciones.add(i4);
        // 5) CAR R(R(3))
        Instruccion i5 = new InstruccionCAR(3, Instruccion.INDIRECTO);
        instrucciones.add(i5);
        return instrucciones;
    }

    public static ArrayList<Instruccion> crearPrograma4() {
        ArrayList<Instruccion> instrucciones = new ArrayList<Instruccion>();

        entrada.append("AB");

        // 1) LEE R(1)
        Instruccion i1 = new InstruccionLEE(1, Instruccion.DIRECTO);
        instrucciones.add(i1);
        // 2) LEE R(2)
        Instruccion i2 = new InstruccionLEE(2, Instruccion.DIRECTO);
        instrucciones.add(i2);
        // 3) CAR R(1)
        Instruccion i3 = new InstruccionCAR(1, Instruccion.DIRECTO);
        instrucciones.add(i3);
        // 4) SUM R(2)
        Instruccion i4 = new InstruccionSUM(2, Instruccion.DIRECTO);
        instrucciones.add(i4);
        return instrucciones;
    }
}
