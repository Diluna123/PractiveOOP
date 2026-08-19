package collectionForOOP;

import java.util.ArrayList;

abstract class Book {

    private int bookID;
    private String bookName;

    public Book(int bookID, String bookName) {
        this.bookID = bookID;
        this.bookName = bookName;

        if(getBookID() <= 0){
            throw new IllegalArgumentException("Book Id must be > 0 ");
        }
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }



    abstract String bookType();


}


interface LibraryServices{

    void addBook(Book book);



    public void displayBook();


}

class Library implements LibraryServices{
    ArrayList<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getBookName()+" Added");
    }

    @Override
    public void displayBook() {
        for(Book book: books){
            System.out.println("Book ID: "+ book.getBookID());
            System.out.println("Book Name : "+book.getBookName());
            System.out.println("Book Type : "+ book.bookType());
            System.out.println("=================================");
        }


    }


}





class PrintedBook extends Book{
    public PrintedBook(int bookID, String bookName) {
        super(bookID, bookName);
    }

    @Override
    String bookType() {
        return "Printed";
    }
}
class EBook extends Book{
    public EBook(int bookID, String bookName) {
        super(bookID, bookName);

    }

    @Override
    String bookType() {
        return "EBook";
    }
}

class AudioBook extends Book{
    public AudioBook(int bookID, String bookName) {
        super(bookID, bookName);
    }

    @Override
    String bookType() {
        return "Audio Book";
    }
}

class Main{
    public static void main(String[] args) {
       Library lib = new Library();
       lib.addBook(new PrintedBook(001, "Madolduwa"));
       lib.addBook(new EBook(002, "Amba Yhaluwo"));
       lib.addBook(new PrintedBook(003, "English Printed"));
        System.out.println("=============================");
       lib.displayBook();



    }
}