package org.roelf.juicepress;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class InstanceManager {
    private static Injector injector = null;

    public static void init() {
        init(new ReflectionsModule());
    }

    public static void init(String basePath) {
        init(new ReflectionsModule(basePath));
    }

    public static void init(AbstractModule... module) {
        if (injector != null)
            throw new IllegalStateException("Injector was already initialized.");

        injector = Guice.createInjector(module);
    }

    public static <T> T getInstance(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    public static Injector getInjector() {
        return injector;
    }
}
