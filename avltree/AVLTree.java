/*
 *  Javier Martínez del Campo González (201507)
 *  
 */
package avltree;


/**
 *
 * @author javier
 */
public class AVLTree <T extends Comparable<T>> {

    private NodoAVL<T> raiz;
    
    public AVLTree() {
    }
    
    
    
    public void inserta(T elemento){
        
        if(elemento == null)
            return;
        if(raiz == null) {
            raiz = new NodoAVL<T>(elemento);
            return;
        }
        
        NodoAVL<T> padre = null;
        NodoAVL<T> actual = raiz;
        while(actual != null && !actual.getElemento().equals(elemento)){
            padre = actual;
            if(actual.getElemento().compareTo(elemento) > 0)
                actual = actual.getIzquierdo();
            else
                actual = actual.getDerecho();
        }
        
        if(actual != null) 
            return;
        
        actual = new NodoAVL(elemento);
        if(padre.getElemento().compareTo(elemento) > 0)
            padre.cuelga(actual, 'i');
        else
            padre.cuelga(actual, 'd');
        
        boolean termine = false;
        while(!termine){
            if(padre.getIzquierdo() == actual)
                padre.setFactorEquilibrio(padre.getFactorEquilibrio() - 1);
            else
                padre.setFactorEquilibrio(padre.getFactorEquilibrio() + 1);
            if(padre.getFactorEquilibrio() == 0)
                termine = true;
            if(padre.getFactorEquilibrio() == 2 || padre.getFactorEquilibrio() == -2){
                if(padre == raiz) {
                    raiz = rota(padre);
                    raiz.setPadre(null);
                }
                else{
                    if(padre.getPadre().getIzquierdo() == padre)
                        padre.getPadre().setIzquierdo(rota(padre));
                    else
                        padre.getPadre().setDerecho(rota(padre));
                }
                termine = true;
            }
            
            if(padre == raiz)
                termine = true;
            
            actual = padre;
            padre = padre.getPadre();
        }
    }
    
    
    
    public void borra(T elemento){
        NodoAVL<T> nodo = buscaNodo(elemento);
        nodo = borraNodo(nodo);
        if(nodo.getPadre() == null)
            return;
        
        boolean termine = false;
        while(!termine){
            if(nodo.getPadre().getElemento().compareTo(nodo.getElemento()) > 0)
                nodo.getPadre().setFactorEquilibrio(nodo.getPadre().getFactorEquilibrio() + 1);
            else
                nodo.getPadre().setFactorEquilibrio(nodo.getPadre().getFactorEquilibrio() - 1);
            if(Math.abs(nodo.getPadre().getFactorEquilibrio()) == 1)
                termine = true;
            if(Math.abs(nodo.getPadre().getFactorEquilibrio()) == 2){
                if(nodo.getPadre() == raiz)
                    raiz = rota(nodo.getPadre());
                else{
                    if(nodo.getPadre().getPadre().getIzquierdo() == nodo.getPadre())
                        nodo.getPadre().getPadre().setIzquierdo(rota(nodo.getPadre()));
                    else
                        nodo.getPadre().getPadre().setDerecho(rota(nodo.getPadre()));
                }
                termine = true;
            }
            
            if(nodo.getPadre() == raiz)
                termine = true;
            
            nodo = nodo.getPadre();
            
        }
        
    }
    
    
    public NodoAVL<T> borraNodo(NodoAVL<T> nodo){
        NodoAVL<T> padre = nodo.getPadre();
        
        if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){
            if(nodo == raiz){
                raiz = null;
                return nodo;
            }
            if(padre.getIzquierdo() == nodo){
                padre.setIzquierdo(null);
            }
            else{
                padre.setDerecho(null);
            }
            return nodo;
        }
        
        else if(nodo.getIzquierdo() == null && nodo.getDerecho() != null){
            if(nodo == raiz){
                raiz = nodo.getDerecho();
                return nodo;
            }
            if(padre.getIzquierdo() == nodo){
                padre.cuelga(nodo.getDerecho(), 'i');
            }
            else{
                padre.cuelga(nodo.getDerecho(), 'd');
            }
            return nodo;
        }
        
        else if(nodo.getIzquierdo() != null && nodo.getDerecho() == null){
            if(nodo == raiz){
                raiz = nodo.getIzquierdo();
                return nodo;
            }
            if(padre.getIzquierdo() == nodo){
                padre.cuelga(nodo.getIzquierdo(), 'i');
            }
            else{
                padre.cuelga(nodo.getIzquierdo(), 'd');
            }
            return nodo;
        }
        
        else {
            NodoAVL<T> siguiente = nodo.getDerecho();
            while(siguiente.getIzquierdo() != null)
                siguiente = siguiente.getIzquierdo();
            nodo.setElemento(siguiente.getElemento());
            return borraNodo(siguiente);
        }
    }
    
    private NodoAVL<T> buscaNodo(T elemento){
        if(elemento == null)
            throw new RuntimeException("Elemento es null…");
        if(raiz == null) {
            throw new RuntimeException("Árbol vacío…");
        }
        
        NodoAVL<T> actual = raiz;
        while(actual != null && !actual.getElemento().equals(elemento)){
            if(actual.getElemento().compareTo(elemento) > 0)
                actual = actual.getIzquierdo();
            else
                actual = actual.getDerecho();
        }
        
        if(actual == null) 
            throw new RuntimeException("Elemento no está…");
        
        return actual;
    }
    
    public boolean contiene(T elemento){
        if(elemento == null)
            return false;
        if(raiz == null) {
            return false;
        }
        
        NodoAVL<T> actual = raiz;
        while(actual != null && !actual.getElemento().equals(elemento)){
            if(actual.getElemento().compareTo(elemento) > 0)
                actual = actual.getIzquierdo();
            else
                actual = actual.getDerecho();
        }
        
        return actual != null;
    }
    
    
    
    private NodoAVL<T> rota(NodoAVL<T> padre){
        NodoAVL<T> alpha, beta, gamma, a, b, c, d;
        alpha = padre;
        
        if(alpha.getFactorEquilibrio() == -2){
            beta = alpha.getIzquierdo();
            d = alpha.getDerecho();
            if(beta.getFactorEquilibrio() == 1){
                a = beta.getIzquierdo();
                gamma = beta.getDerecho();
                b = gamma.getIzquierdo();
                c = gamma.getDerecho();
                
                gamma.cuelga(beta, 'i');
                gamma.cuelga(alpha, 'd');
                beta.cuelga(b, 'd');
                alpha.cuelga(c, 'i');
                
                switch(gamma.getFactorEquilibrio()){
                    case -1:
                        beta.setFactorEquilibrio(0);
                        alpha.setFactorEquilibrio(1);
                        gamma.setFactorEquilibrio(0);
                        break;
                    case 1:
                        beta.setFactorEquilibrio(-1);
                        alpha.setFactorEquilibrio(0);
                        gamma.setFactorEquilibrio(0);
                        break;
                    case 0:
                        beta.setFactorEquilibrio(0);
                        alpha.setFactorEquilibrio(0);
                        gamma.setFactorEquilibrio(0);
                        break;
                }
                padre = gamma;
            }
            else{
                c = beta.getDerecho();
                
                beta.setPadre(alpha.getPadre());
                beta.cuelga(alpha, 'd');
                alpha.cuelga(c, 'i');
                
                alpha.setFactorEquilibrio(Math.abs(beta.getFactorEquilibrio()) - 1);
                beta.setFactorEquilibrio(-(beta.getFactorEquilibrio() + 1));
                
                padre = beta;
            }
        }
        
        
        else{
            beta = alpha.getDerecho();
            d = alpha.getIzquierdo();
            if(beta.getFactorEquilibrio() == -1){
                a = beta.getDerecho();
                gamma = beta.getIzquierdo();
                b = gamma.getDerecho();
                c = gamma.getIzquierdo();
                
                gamma.cuelga(beta, 'd');
                gamma.cuelga(alpha, 'i');
                beta.cuelga(b, 'i');
                alpha.cuelga(c, 'd');
                
                switch(gamma.getFactorEquilibrio()){
                    case 1:
                        beta.setFactorEquilibrio(0);
                        alpha.setFactorEquilibrio(-1);
                        gamma.setFactorEquilibrio(0);
                        break;
                    case -1:
                        beta.setFactorEquilibrio(1);
                        alpha.setFactorEquilibrio(0);
                        gamma.setFactorEquilibrio(0);
                        break;
                    case 0:
                        beta.setFactorEquilibrio(0);
                        alpha.setFactorEquilibrio(0);
                        gamma.setFactorEquilibrio(0);
                        break;
                }
                padre = gamma;
            }
            else{
                c = beta.getIzquierdo();
                
                beta.setPadre(alpha.getPadre());
                beta.cuelga(alpha, 'i');
                alpha.cuelga(c, 'd');
                
                alpha.setFactorEquilibrio(Math.abs(beta.getFactorEquilibrio() - 1));
                beta.setFactorEquilibrio(beta.getFactorEquilibrio() - 1);
                
                padre = beta;
            }
        }
        return padre;
    }
    
    
    
    
    
    
    
    
}

