package uni.aed.biblioteca;

public class Autor {
    public String name;
    public BookList books = new BookList();
    public Autor(){
    }
    public boolean equals(Object node){
        return name.equals(((Autor) node).name);
    }
    public void display(){
        System.out.println(name);
            books.display();
    }
}
    


