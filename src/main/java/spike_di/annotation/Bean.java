package spike_di.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Proxy;

/**
 * This annotation marks beans. If @Context.packageScan() method scan class with this annotation it
 * will be added to the context.
 *
 * Param name is vital. It specify bean name in context.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bean {
   String value();
}
