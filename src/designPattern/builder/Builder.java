package designPattern.builder;

/**
 * 建造者模式的建造者：里面的东西有点像各个工人，一砖一瓦，它决定了工人用哪个工人哪种工人，砖瓦用什么样子的砖瓦
 */
public abstract class Builder {

    protected abstract void buildTitle (String title);
    protected abstract void buildString (String str);
    protected abstract void buildItems (String[] items);
    protected abstract void buildDone();


    // 在不影响 Director的construct情况下，保证必须要在先调用makeTitle后，才能调用后面的步骤
    private boolean initialized = false;
    public void makeTitle (String title) {
        if (!initialized) {
            buildTitle(title);
            initialized = true;
        }
    }

    public void makeString (String str) {
        if (initialized) {
            buildString(str);
        }
    }

    public void makeItems (String[] items) {
        if (initialized) {
            buildItems(items);
        }
    }

    public void close() {
        if (initialized) {
            buildDone();
        }
    }
}
