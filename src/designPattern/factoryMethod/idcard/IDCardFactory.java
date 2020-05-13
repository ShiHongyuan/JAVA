package designPattern.factoryMethod.idcard;

import designPattern.factoryMethod.framework.Factory;
import designPattern.factoryMethod.framework.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生产某一种具体产品实例的某一种具体工厂
 */
public class IDCardFactory extends Factory {
    private List<String> owners = new ArrayList<>();
    private Map<Integer, String> databases = new HashMap<>();

    private int serial = 1;

    // idcard包外的类无法使用createProduct，registerProduct方法，必须使用抽象工厂父类的public create方法
    // 方法定义成synchronized，因为为了防止多线程可能产生相同编号的实例
    protected synchronized Product createProduct(String owner) {
        return new IDCard(owner, serial++);
    }

    @Override
    protected void registerProduct(Product product) {
        owners.add(((IDCard)product).getOwner());
        databases.put(((IDCard)product).getSerial(), ((IDCard)product).getOwner());
    }

    public List getOwners() {
        return owners;
    }

    public Map getDatabases() {
        return databases;
    }
}
