package uni.aed.biblioteca;


public class Patron {
    public String name;
    public BookList books = new BookList();
    public Patron() {
    }
    public boolean equals(Object node) {
        return name.equals(((Patron) node).name);
    }
    public void display(){
        if(!books.isEmpty()){
            System.out.println(name + " tiene los libros siguientes: ");
            books.display();
        }
        else
            System.out.println(name + "no tiene libros");
    }
}
