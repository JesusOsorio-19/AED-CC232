package uni.aed.stack;

import uni.aed.listTDA.NoSuchElementException;

public class ArrayStackTDA<E> implements StackTDA<E> {
        private static final int TAM_DEFINIDO=25;
        private E[] elementos;
        private int contador;

    public ArrayStackTDA() {
        this(TAM_DEFINIDO);
    }

    public ArrayStackTDA(int tamanio) {        
        if(tamanio<=0){
            throw new IllegalArgumentException("Capacidad inicial debe ser positiva");
        }
        elementos=(E[])new Object[tamanio];
        contador=0;                    
    }    
        
    @Override
    public void push(E elemento) {
        if(contador==elementos.length)
            expande();
        elementos[contador++]=elemento;
    }

    @Override
    public E pop() throws StackEmptyExceptionTDA {
        if (isEmpty())
            throw new StackEmptyExceptionTDA();
        else{
            contador--;
            E item=elementos[contador];
            elementos[contador]=null;
            return item;
        }
    }

    @Override
    public E peek() throws StackEmptyExceptionTDA {
        if (isEmpty())
            throw new StackEmptyExceptionTDA();
        else
            return (E)elementos[contador-1]; 
    }

    @Override
    public int size() {
        return contador;
    }

    @Override
    public void clear() {
        for (int i=0;i<contador;i++){
            elementos[i]=null;
        }
        contador=0;
    }

    @Override
    public boolean isEmpty() {
        return contador==0;
    }
         
    private void expande(){
        int nuevaLongitud=(int)(1.5*elementos.length);
        E[] temp=(E[])new Object[nuevaLongitud];
        for(int i=0;i<elementos.length;i++)        
            temp[i]=elementos[i];
        elementos=temp;        
    }

    @Override
    public String toString() {
        String result="";
        int current=contador-1;
        while(current>=0){
            if(result.trim().length()==0)
                result=elementos[current].toString();
            else
                result=result+"\n"+elementos[current].toString();
            current--;
        }
        return result;
    }

    
}
