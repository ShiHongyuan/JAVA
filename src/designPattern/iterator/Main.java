package designPattern.iterator;

/**
 * 迭代器模式：
 *      实现了Iterator的具体迭代器，可以被相应的实现了Aggregate的具体集合类创建
 *      Aggregate的具体集合类创建了Iterator的具体迭代器后，将自己传递给迭代器，迭代器就可以独自遍历访问这个集合了
 *
 *      迭代器模式将迭代器和数据结构对象分离开来，如果数据结构对象改变了数据结构，遍历的方式也会改变，
 *      但是遍历算法没有在数据结构对象中实现，而是在迭代器对象中实现，所以只需要修改迭代器对象，或者替换新的迭代器对象即可。
 *      但是使用者获取的迭代器对象是 Iterator，是所有迭代器对象的父类接口，所以即使替换了新的迭代器对象返回，
 *      使用者遍历迭代器的代码也不需要任何改变。
 *
 *      所以要优先使用抽象类和接口来编程，而不是只使用具体类来编程。
 *      具体类在后续替换的时候太麻烦了，涉及到的代码段太多，但是优先使用抽象类和接口，很多地方就不需要修改了。
 */
public class Main {
    public static void main (String[] args) {
        // 被遍历对象的数组结构是数组
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));
        bookShelf.appendBook(new Book("Daddy-Long-Legs"));

        System.out.println("被遍历对象的数组结构是数组 ------------------");
        getAndShowIterator(bookShelf);

        // 被遍历对象的数组结构是ArrayList
        BookShelfArrayLIst bookShelfArrayLIst = new BookShelfArrayLIst();
        bookShelfArrayLIst.appendBook(new Book("Around the World in 80 Days"));
        bookShelfArrayLIst.appendBook(new Book("Bible"));
        bookShelfArrayLIst.appendBook(new Book("Cinderella"));
        bookShelfArrayLIst.appendBook(new Book("Daddy-Long-Legs"));

        System.out.println("被遍历对象的数组结构是ArrayList ------------------");
        getAndShowIterator(bookShelfArrayLIst);

    }

    // 遍历的代码不用变
    public static void getAndShowIterator (Aggregate aggregate) {
        Iterator it = aggregate.iterator();
        while (it.hasNext()) {
            Book book = (Book) it.next();
            System.out.println(book.getName());
        }
    }
}
