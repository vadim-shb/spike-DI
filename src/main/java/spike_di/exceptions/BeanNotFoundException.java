package spike_di.exceptions;

public class BeanNotFoundException extends RuntimeException {
    public BeanNotFoundException(String beanName) {
        super("Bean with name: '" + beanName + "' not defined");
    }
}
