/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saats;

/**
 *
 * @author misayo
 */
public class VentasRepetidas {
    private String id;
    private Integer contador;

    public VentasRepetidas() {
    }

    public VentasRepetidas(String id, Integer contador) {
        this.id = id;
        this.contador = contador;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String toString(){
        return id + " " + contador;
    }
}
