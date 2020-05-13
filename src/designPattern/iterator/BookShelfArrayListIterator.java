package designPattern.iterator;

public class BookShelfArrayListIterator implements Iterator{
    private BookShelfArrayLIst bookShelfArrayLIst;
    private int index;

    public BookShelfArrayListIterator (BookShelfArrayLIst bookShelfArrayLIst) {
        this.bookShelfArrayLIst = bookShelfArrayLIst;
    }


    @Override
    public boolean hasNext() {
        if (index < bookShelfArrayLIst.getLength()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Object next() {
        Book book = bookShelfArrayLIst.getBookAt(index);
        index ++;
        return book;
    }
}
