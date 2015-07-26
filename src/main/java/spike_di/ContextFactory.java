package spike_di;

import spike_di.exceptions.BeanNotFoundException;
import spike_di.exceptions.ContextNotCreatedException;
import spike_di.exceptions.DuplicateBeanNameException;

public class ContextFactory {
    public static Context buildContext(Class contextConfigClass) {
        Context retval = new Context();
        try {
            retval.addConfiguration(contextConfigClass);
            retval.injectDependencies();
        } catch (DuplicateBeanNameException | BeanNotFoundException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ContextNotCreatedException();
        }
        return retval;
    }
}
