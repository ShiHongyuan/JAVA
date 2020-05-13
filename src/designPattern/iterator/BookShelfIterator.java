package designPattern.iterator;

public class BookShelfIterator implements Iterator{
    private BookShelf bookShelf;
    private int index;
    public BookShelfIterator (BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    @Override
    public boolean hasNext() { //返回当前指针是否存在元素
        if (index < bookShelf.getLength()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Object next() { //返回当前指针的元素后，才移动到下一个指针
        Book book = bookShelf.getBookAt(index);
        index ++;
        return book;
    }
}
