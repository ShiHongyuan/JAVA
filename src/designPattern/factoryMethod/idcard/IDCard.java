package designPattern.factoryMethod.idcard;

import designPattern.factoryMethod.framework.Product;

/**
 * 工厂生产的产品的其中一种产品，具体类
 */
public class IDCard extends Product {
    private String owner;
    private int serial;

    // idcard包外的类无法通过new创建IDCard的实例，必须通过工厂IDCardFactory来创建IDCard的实例
    IDCard(String owner) {
        System.out.println("制作" + owner + "的ID卡。");
        this.owner = owner;
    }

    IDCard(String owner, int serial) {
        System.out.println("制作" + owner + "(" + serial + ")" + "的ID卡。");
        this.owner = owner;
        this.serial = serial;
    }

    @Override
    public void use() {
        System.out.println("使用" + owner + "(" + serial + ")" + "的ID卡。");
    }

    public String getOwner() {
        return owner;
    }

    public int getSerial() {
        return serial;
    }
}
