/*
 *  Javier Martínez del Campo González (201507)
 *  
 */
package avltree;

import java.util.Objects;

/**
 *
 * @author javier
 */
public class NodoAVL <T extends Comparable<T>> {
    
    private T elemento;
    private NodoAVL<T> padre;
    private NodoAVL<T> izquierdo;
    private NodoAVL<T> derecho;
    private int factorEquilibrio;

    public NodoAVL() {
        this.factorEquilibrio = 0;
    }

    public NodoAVL(T elemento) {
        this.elemento = elemento;
        this.factorEquilibrio = 0;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public void setPadre(NodoAVL<T> padre) {
        this.padre = padre;
    }

    public void setIzquierdo(NodoAVL<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVL<T> derecho) {
        this.derecho = derecho;
    }

    public void setFactorEquilibrio(int factorEquilibrio) {
        this.factorEquilibrio = factorEquilibrio;
    }

    public T getElemento() {
        return elemento;
    }

    public NodoAVL<T> getPadre() {
        return padre;
    }

    public NodoAVL<T> getIzquierdo() {
        return izquierdo;
    }

    public NodoAVL<T> getDerecho() {
        return derecho;
    }

    public int getFactorEquilibrio() {
        return factorEquilibrio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.elemento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodoAVL<?> other = (NodoAVL<?>) obj;
        return Objects.equals(this.elemento, other.elemento);
    }

    @Override
    public String toString() {
        return "NodoAVL{" + "elemento=" + elemento + ", factorEquilibrio=" + factorEquilibrio + '}';
    }

    public void cuelga(NodoAVL<T> nuevo, char lado){ //lado = i(izquierda) ó d(derecha)
        if(lado == 'i')
            this.setIzquierdo(nuevo);
        else
            if(lado == 'd')
                this.setDerecho(nuevo);
            else
                throw new RuntimeException("Lado inválido");
        if(nuevo != null)
            nuevo.setPadre(this);
    }
    
    
    
    
}
