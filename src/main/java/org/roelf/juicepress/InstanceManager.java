package org.roelf.juicepress;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.HashSet;
import java.util.Set;

public class InstanceManager {
    private static Injector injector = null;
    private final static Set<AbstractModule> modules = new HashSet<>();

    public static void init() {
        init(modules.toArray(new AbstractModule[modules.size()]));
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

    public static Set<? extends AbstractModule> getModules() {
        return modules;
    }

    public static void addModule(AbstractModule module) {
        modules.add(module);
    }

    public static void removeModule(AbstractModule module) {
        modules.remove(module);
    }

    public static void addDefaultReflectionModule(String basePath) {
        addModule(new ReflectionsModule(basePath));
    }

    public static void addDefaultReflectionModule() {
        addModule(new ReflectionsModule());
    }
}
