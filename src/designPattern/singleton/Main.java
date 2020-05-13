package designPattern.singleton;

/**
 * 单例：只能生成一个实例
 *
 *      确保任何情况下都只有一个实例，那么就可以在只有一个实例的前提下进行编程，就不用考虑多个实例时，
 *      实例之间相互影响产生的bug了。
 *      所以，如果程序确实只需要一个实例，就使用单例模式，可以简化编程中需要考虑方方面面问题的复杂性。也不用担心不小心生成了多个实例了。
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Start.");
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        if (obj1 == obj2) {
            System.out.println("obj1 与 obj2 是相同的实例。");
        } else {
            System.out.println("obj1 与 obj2 是不同的实例。");
        }
        System.out.println("End.");


        System.out.println("Start.");
        new Thread(new Runnable() {
            @Override
            public void run() {
                TicketMaker ticketMaker = TicketMaker.getInstance();
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + ticketMaker.getNextTicketNumber());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                TicketMaker ticketMaker = TicketMaker.getInstance();
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + ticketMaker.getNextTicketNumber());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                TicketMaker ticketMaker = TicketMaker.getInstance();
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + ticketMaker.getNextTicketNumber());
                }
            }
        }).start();
        System.out.println("End.");


        System.out.println("Start.");
        for (int i = 0; i < 9; i++) {
            Triple triple = Triple.getInstance(i % 3);
            System.out.println(i + ":" + triple);
        }
        System.out.println("End.");


        System.out.println("Start.");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton2 singleton2 = Singleton2.getInstance();
                System.out.println(Thread.currentThread().getName() + ": obj =" + singleton2);
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton2 singleton2 = Singleton2.getInstance();
                System.out.println(Thread.currentThread().getName() + ": obj =" + singleton2);
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton2 singleton2 = Singleton2.getInstance();
                System.out.println(Thread.currentThread().getName() + ": obj =" + singleton2);
            }
        }, "C").start();
        System.out.println("End.");


    }

}
