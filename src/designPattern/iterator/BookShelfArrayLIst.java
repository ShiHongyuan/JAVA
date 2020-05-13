package designPattern.iterator;

import java.util.ArrayList;

public class BookShelfArrayLIst implements Aggregate{
    private ArrayList<Book> books;

    public BookShelfArrayLIst () {
        books = new ArrayList<Book>();
    }

    public Book getBookAt (int index) {
        return books.get(index);
    }
    public void appendBook (Book book) {
        books.add(book);
    }
    public int getLength () {
        return books.size();
    }
    public Iterator iterator () {
        return new BookShelfArrayListIterator(this);
    }
}
