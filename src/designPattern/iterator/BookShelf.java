package designPattern.iterator;

/**
 * 具体的迭代器 - 书架
 */
public class BookShelf implements Aggregate {
    private Book[] books;
    private int last = 0;
    public BookShelf (int maxsize) {
        books = new Book[maxsize];
    }
    public Book getBookAt (int index) {
        return books[index];
    }
    public void appendBook (Book book) {
        books[last++] = book;
    }
    public int getLength () {
        return last;
    }
    public Iterator iterator () {
        return new BookShelfIterator(this);
    }
}
