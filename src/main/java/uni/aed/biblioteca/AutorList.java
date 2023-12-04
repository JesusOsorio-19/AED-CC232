package uni.aed.biblioteca;
import java.util.LinkedList;

public class AutorList extends LinkedList{
    public AutorList(){
       super();
    }   
    public void display(){
        Object[] authors = toArray();
        for(int i=0; i <authors.length; i++)
        ((Autor )authors[i]).display();
    }
}
