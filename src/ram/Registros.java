package ram;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Parisi Germán
 */
public class Registros {
    private List<Registro> registros;
    public Registros(){
        this.registros = new ArrayList<>();
    }
    
    private Registro crearRegistro(long numero){
        Registro registro = new Registro(numero);
        registros.add(registro);
        return registro;
    }
    
    /**
     * 
     * @param numero es el número del registro.
     * @param valor es el valor del registro.
     * @return true si se pudo establecer el valor en el registro, false en
     * caso contrario.
     */
    public boolean setRegistro(long numero, long valor){
        if(numero <= 0){
            return false;
        }
        Registro registro = getRegistro(numero);
        registro.setValor(valor);
        return true;
    }
    
    public Registro getRegistro(long numero){
        for (Registro registro : registros) {
            if(registro.getNumero() == numero){
                return registro;
            }
        }
        return crearRegistro(numero);
    }
    
    public List<Registro> getRegistros(){
        return this.registros;
    }
}
