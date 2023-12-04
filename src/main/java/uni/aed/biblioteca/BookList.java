package uni.aed.biblioteca;
import java.util.LinkedList;

public class BookList extends LinkedList {
    public BookList(){
        super();
    }
    public void display(){
        for(int i=0; i<size(); i++)
            System.out.print(get(i));
    }
}
