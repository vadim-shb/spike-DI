package spike_di.exceptions;

public class DuplicateBeanNameException extends RuntimeException {
    public DuplicateBeanNameException(String beanName) {
        super("Can not create two beans with the same name: " + beanName);
    }
}
