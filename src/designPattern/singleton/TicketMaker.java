package designPattern.singleton;

public class TicketMaker {
    private static TicketMaker ticketMaker = new TicketMaker();
    private int ticket = 1000;
    private TicketMaker() {}
    public static TicketMaker getInstance() {
        return ticketMaker;
    }
    // 加 synchronized，防止多线程返回相同的ticket
    public synchronized int getNextTicketNumber () {
        return ticket++;
    }
}
