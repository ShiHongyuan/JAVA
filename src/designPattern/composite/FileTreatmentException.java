package designPattern.composite;

/**
 * 容器模式：叶子不能添加Entry，不重写add方法，Entry接口的add原方法抛出的异常
 */
public class FileTreatmentException extends RuntimeException {
    public FileTreatmentException() {

    }
    public FileTreatmentException(String msg) {
        super(msg);
    }
}
