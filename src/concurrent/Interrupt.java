package concurrent;

/**
 * 通过抛出异常，取消一些线程的继续运行
 */
public class Interrupt {
    public static void main (String[] args) {
        /**
         * java.lang.Thread类的 interrupt方法：
         *      是线程的实例方法，指定线程对象实例调用。调用时不需要获取实例的锁，任何线程都可以指定其他线程执行interrupt。
         *
         *      interrupt方法只是改变了线程的状态，线程的中断状态分为中断状态和非中断状态，
         *      interrupt方法把线程的中断状态变为中断状态，并不会抛出InterruptedException异常，
         *      需要线程的某些代码自己去检测当前的状态，根据状态判断现在是否抛出InterruptedException异常。
         *      抛出异常后，可以通过catch代码块捕捉和处理，或者抛给调用线程的主线程，从而中止线程。
         *
         *      线程中已经实现检测中断状态并抛出异常的几个方法：（抛出异常，并且清除中断状态）
         *          1. java.lang.Object类的 wait
         *          2. java.lang.Thread类的 sleep
         *          3. java.lang.Thread类的 join
         *              join是当前线程要等待指定线程终止后，才能继续执行。如果当前线程（调用join的线程）被中断，当前线程抛出异常，中断状态被清除
         *
         *
         *      除以上三个方法外，线程中其他的代码，包括计算、赋值、for、while、if等都不会检查中断状态，
         *      所以对线程的interrupt方法不感冒，不会抛出异常，继续执行。
         *      除非自己实现检查线程中断状态并抛出异常。
         *          1. isInterrupted() 是线程实例的方法，检查指定线程的中断状态，当前处于中断状态，返回true，处于非中断状态，返回false。
         *          2. Thread.interrupted() 是Thread的静态方法，检查并且恢复当前线程为非中断状态，
         *                                  当前处于中断状态，返回true，处于非中断状态，返回false，并且把状态设置为非中断状态。
         *                                  因为是Thread的静态方法，只能在需要判断状态的线程中使用，不能由其他线程调用判断。
         *
         *
         *
         * java.lang.Thread类的 stop方法：
         *      已经废弃，不管线程处于什么状态，都会立刻终止运行中的线程，如果线程处于临界区操作也会终止，
         *      所以会破坏安全性，已经不建议使用。
         *
         */


        /**
         * java.lang.Object类的 wait：
         *      对正在 wait的线程调用 interrupt()，线程会被唤醒，然后首先获取锁，再抛出 InterruptedException异常，没有获取锁之前，不会抛出异常。
         *      抛出异常后，怎么处理，是catch块里处理，还是方法向外继续抛出异常 throw InterruptedException，就看代码怎么写了。
         *
         *      注意：调用 notify/notifyAll 时，需要先获取锁，但是调用 interrupt 时，不需要获取线程实例的锁。
         */
        Thread alice = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    excutor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            private synchronized void excutor() throws InterruptedException{
                wait();
            }
        });
        // 指定alice这个线程执行 interrupt
        alice.interrupt();

        /**
         * java.lang.Thread类的 sleep：
         *      对正在 sleep的线程调用 interrupt()，线程会终止待运行状态，不需要获取锁，直接抛出 InterruptedException异常。
         *      抛出异常后，怎么处理，是catch块里处理，还是方法向外继续抛出异常 throw InterruptedException，就看代码怎么写了。
         */
        alice = new Thread(){
            @Override
            public void run() {
                try {
                    excutor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    stop();
                }
            }
            private void excutor() throws InterruptedException{
                Thread.sleep(10000);
            }
        };
        // 指定alice这个线程执行 interrupt
        alice.interrupt();

        /**
         * java.lang.Thread类的 join：
         *      对正在等待join的线程执行完的外线程调用 interrupt()，线程会终止等待，不需要获取锁，直接抛出 InterruptedException异常。
         *      抛出异常后，怎么处理，是catch块里处理，还是方法向外继续抛出异常 throw InterruptedException，就看代码怎么写了。
         */
        alice = new Thread(){
            @Override
            public void run() {
                Thread thread = new Thread();
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        // 指定alice这个线程执行 interrupt
        alice.interrupt();









    }

}
