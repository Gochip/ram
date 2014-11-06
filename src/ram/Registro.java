package ram;

import java.math.BigInteger;

/**
 * @author Parisi Germán
 */
public class Registro {

    private long valor;
    private long numero;

    public Registro(long numero){
        this.numero = numero;
    }
    
    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public long getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return this.numero + ") " + this.valor;
    }
}
