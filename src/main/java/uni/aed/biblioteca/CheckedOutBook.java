package uni.aed.biblioteca;

public class CheckedOutBook {
    public Autor autor = null;
    public Book book = null;
    public CheckedOutBook() {
    }
    public boolean equals(Object node){
        return book.title.equals(((CheckedOutBook) node).book.title) && 
                autor.name.equals(((CheckedOutBook) node).autor.name);
    }
    public String toString(){
        return "     * " + autor.name + ", " + book.title + "\n";
    }
}
