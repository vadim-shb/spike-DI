package spike_di;

import spike_di.annotation.Bean;
import spike_di.annotation.Executable;
import spike_di.exceptions.BeanNotFoundException;
import spike_di.exceptions.DuplicateBeanNameException;
import spike_di.exceptions.WrongExecutableDeclarationException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class Context {

    ContextBeansVault beansVault = new ContextBeansVault();

    public void addConfiguration(Class configClass) throws IllegalAccessException, ClassNotFoundException, InstantiationException, DuplicateBeanNameException {
        scanConfigFields(configClass);
        scanConfigMethods(configClass);

    }

    private void addSingletonBean(String beanName, Class beanClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DuplicateBeanNameException {
        beansVault.addEmptySingletonBean(beanName, beanClass);
    }

    public void runExecutables() {
        Collection allBeans = beansVault.getAllBeans();
        allBeans.stream().forEach(Context::runBeanExecutables);
    }

    private static void runBeanExecutables(Object bean) {
        Method[] beanMethods = bean.getClass().getMethods();
        Arrays.stream(beanMethods).forEach(method -> {
            if (method.getAnnotation(Executable.class) != null) {
                try {
                    method.invoke(bean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new WrongExecutableDeclarationException();
                }
            }
        });
    }

    private void scanConfigFields(Class contextConfig) throws InstantiationException, IllegalAccessException, ClassNotFoundException, DuplicateBeanNameException {
        Field[] fields = contextConfig.getFields();
        for (Field field:fields){
            Bean beanAnnotation = field.getAnnotation(Bean.class);
            if (beanAnnotation != null) {
                addSingletonBean(beanAnnotation.value(), field.getType());
            }
        }

    }

    private void scanConfigMethods(Class contextConfig) {
        // add possibility to creates beans by methods
    }

    public void injectDependencies() throws IllegalAccessException, BeanNotFoundException {
        beansVault.injectBeansDependencies();
    }
}
