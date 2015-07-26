package spike_di;

import spike_di.annotation.Inject;
import spike_di.exceptions.BeanNotFoundException;
import spike_di.exceptions.DuplicateBeanNameException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ContextBeansVault {
    private Map<String, Object> singletonBeans = new HashMap<>();

    public void addEmptySingletonBean(String name, Class beanClass) throws InstantiationException, IllegalAccessException, DuplicateBeanNameException {
        if (singletonBeans.get(name) != null) throw new DuplicateBeanNameException(name);

        singletonBeans.put(name, createBeanInstance(beanClass));
    }

    public void injectBeansDependencies() throws IllegalAccessException, BeanNotFoundException {
        for (Object beanName : singletonBeans.values()) {
            injectBeanDependencies(beanName);
        }
    }

    private Object createBeanInstance(Class beanClass) throws IllegalAccessException, InstantiationException {
        return beanClass.newInstance();
    }

    private void injectBeanDependencies(Object acceptorBean) throws IllegalAccessException, BeanNotFoundException {
        Field[] acceptorFields = acceptorBean.getClass().getDeclaredFields();
        for (Field field : acceptorFields) {
            Inject injectAnnotation = field.getAnnotation(Inject.class);
            if (injectAnnotation != null) {
                String beanNameToInject = injectAnnotation.value();
                Object beanToInject = singletonBeans.get(beanNameToInject);
                if (beanToInject == null) throw new BeanNotFoundException(beanNameToInject);
                field.setAccessible(true);
                field.set(acceptorBean, beanToInject);
            }
        }
    }

    public Collection getAllBeans() {
        return singletonBeans.values();

    }
}
