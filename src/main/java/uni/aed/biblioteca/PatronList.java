package uni.aed.biblioteca;
import java.util.LinkedList;


public class PatronList extends LinkedList {
    public PatronList(){
        super();
    }
    public void display() {
        for (java.util.Iterator it = iterator(); it.hasNext(); )
            ((Patron) it.next()).display();
    }
}
