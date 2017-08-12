package org.roelf.juicepress.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * One can annotate classes extending {@link com.google.inject.Provider} with this to enable automatic provider binding.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProvidesType {
    Class<?> value();
}
