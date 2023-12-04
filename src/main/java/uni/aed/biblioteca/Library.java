package uni.aed.biblioteca;
import java.io.*;

public class Library {
    private AutorList[] catalog = new AutorList[(int)('Z'+1)];
    private PatronList[] people = new PatronList[(int)('Z'+1)];
    private String input;
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    public Library(){
        for(int i = 0; i <= (int) 'Z'; i++){
            catalog[i] = new AutorList();
            people[i] = new PatronList();
        }
    }
    private String getString(String msg) {
        System.out.println(msg + " ");
        System.out.flush();
        try {
            input = buffer.readLine();
        }
        catch (IOException io){
        }
        return input.substring(0,1).toUpperCase() + input.substring(1);       
    }
    private void status(){
        System.out.println("La biblioteca tiene los libros siguientes:\n");
        for (int i = (int) 'A'; i <= (int) 'Z'; i++)
            if(!catalog[i].isEmpty())catalog[i].display();
        System.out.println("\n Las personas siguientes están utilizando la biblioteca: \n");
        for (int i = (int) 'A'; i <= (int) 'Z'; i++)
            if(!people[i].isEmpty())people[i].display();
    }
    private void includeBook(){
        Autor newAutor = new Autor();
        int oldAutor;
        Book newBook = new Book();
        newAutor.name = getString("Introduzca el nombre del autor: ");
        newBook.title = getString("Introduzca el título del libro: ");
        oldAutor = catalog[(int) newAutor.name.charAt(0)].indexOf(newAutor);
        if(oldAutor == -1) {
            newAutor.books.add(newBook);
            catalog[(int) newAutor.name.charAt(0)].add(newAutor);
        }
        else ((Autor)catalog[(int)newAutor.name.charAt(0)].get(oldAutor)).books.add(newBook);        
    }
    private void checkOutBook(){
        Patron patron = new Patron(), patronRef; // = new Patron();
        Autor autor = new Autor(), autorRef = new Autor();
        Book book = new Book();
        int patronIndex, bookIndex = -1, autorIndex = -1;
        patron.name = getString("Tntroduzca el nombre del usuario: ");
        while (autorIndex == -1){
            autor.name = getString("Tntroduzca el nombre del autor: ");
            autorIndex = catalog [(int) autor.name.charAt(0)].indexOf(autor);
            if(autorIndex == -1)
                System.out.println("Nombre de autor mal escrito.");
        }
        while (bookIndex == -1){
            book.title = getString("Introduzca el título del libro: ");
            autorRef = (Autor) catalog[(int) autor.name.charAt(0)].get(autorIndex);
            bookIndex= autorRef.books.indexOf(book);
            if(bookIndex == -1)
                System.out.println("Título mal escrito");
        }
        Book bookRef = (Book) autorRef.books.get(bookIndex);
        CheckedOutBook bookToCheckOut = new CheckedOutBook();
        bookToCheckOut.autor = autorRef;
        bookToCheckOut.book =  bookRef;
        patronIndex = people[(int) patron.name.charAt(0)].indexOf(patron);
        if (patronIndex == -1) {
            patron.books.add(bookToCheckOut);
            people[(int) patron.name.charAt(0)].add(patron);
            bookRef.patron = (Patron) people [(int) patron.name.charAt(0)].getFirst();
        }
        else {
            patronRef = (Patron) people[(int) patron.name.charAt(0)].get(patronIndex);
            patronRef.books.add(bookToCheckOut);
            bookRef.patron = patronRef;
        }   
    }
    private void returnBook(){
        Patron patron = new Patron();
        Book book = new Book();
        Autor autor = new Autor(), autorRef = new Autor();
        int patronIndex = -1, bookIndex = -1, autorIndex = -1;
        while (patronIndex == -1){
            patron.name = getString("Introduzca el nombre del usuario:");
            patronIndex = people[(int) patron.name.charAt(0)].indexOf(patron);
            if(patronIndex == -1)
                System.out.println("Nombre del usuario mal escrito");
        }
        while (autorIndex == -1){
            autor.name = getString("Introduzca el nombre del autor:");
            autorIndex = catalog[(int) autor.name.charAt(0)].indexOf(autor);
            if (autorIndex == -1)
                System.out.println("Nombre de autor mal escrito");            
        }
        
        while(bookIndex == -1) {
            book.title = getString("Introduzca el título del libro:");
            autorRef = (Autor) catalog[(int) autor.name.charAt(0)].get(autorIndex);
            bookIndex = autorRef.books.indexOf(book);
            if (bookIndex == -1 )
                System.out.println("Título mal escrito");
        }
        CheckedOutBook checkedOutBook = new CheckedOutBook();
        checkedOutBook.autor = autorRef;
        checkedOutBook.book = (Book) autorRef.books.get(bookIndex);
        ((Book)autorRef.books.get(bookIndex)).patron = null;
        ((Patron)people[(int) patron.name.charAt(0)].get(patronIndex)).books.remove(checkedOutBook);
        
    }
    public void run() {
        while (true){
            char option = getString("\n Introduzca una de las siguientes" + "opciones:\n" +
                    "1. Incluir un libro en el catálogo\n" +
                    "2. Registrar el préstamo de un libro\n" +
                    "3. Registrar la devolución de un libro \n4.Status\n5." +
                    "Salir\n" +
                    "Su opción:").charAt(0);
            switch(option){
                case '1' : includeBook(); break;
                case '2' : checkOutBook(); break;
                case '3' : returnBook(); break;
                case '5' : return;
                default: System.out.println("Opción inválida, intente de nuevo.");
            }

        }
    }
    public static void main(String args[]){
        (new Library()).run();
    }
}
    