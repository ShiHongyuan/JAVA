package designPattern.singleton;

public class Triple {
    private static Triple triple0 = new Triple(0);
    private static Triple triple1 = new Triple(1);
    private static Triple triple2 = new Triple(2);
    private static Triple[] triples = new Triple[]{triple0, triple1, triple2};

    private int id;

    private Triple(int id) {
        this.id = id;
    }

    public static Triple getInstance(int id) {
        return triples[id];
    }

    @Override
    public String toString() {
        return "[Triple id=" + id + "]";
    }
}
