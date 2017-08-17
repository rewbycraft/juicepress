package org.roelf.juicepress;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.HashSet;
import java.util.Set;

/**
 * Main class of the library.
 */
public class InstanceManager {
    private static Injector injector = null;
    private final static Set<AbstractModule> modules = new HashSet<>();

    /**
     * Init the library using the set of modules.
     */
    public static void init() {
        init(modules.toArray(new AbstractModule[modules.size()]));
    }

    /**
     * Initialize the library using a given set of modules.
     * @param module Modules to initialize the library with.
     */
    public static void init(AbstractModule... module) {
        if (injector != null)
            throw new IllegalStateException("Injector was already initialized.");

        injector = Guice.createInjector(module);
    }

    /**
     * Usability helper. Proxies to guice.
     * @param clazz class to look for.
     * @return Instance of class.
     */
    public static <T> T getInstance(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    public static Injector getInjector() {
        return injector;
    }

    public static Set<? extends AbstractModule> getModules() {
        return modules;
    }

    /**
     * Add a module to the set.
     * @param module Module to add.
     */
    public static void addModule(AbstractModule module) {
        modules.add(module);
    }

    /**
     * Remove a module from the set.
     * @param module Module to remove.
     */
    public static void removeModule(AbstractModule module) {
        modules.remove(module);
    }

    /**
     * Add the included reflections module.
     * @param basePath
     */
    public static void addDefaultReflectionModule(String basePath) {
        addModule(new ReflectionsModule(basePath));
    }

    /**
     * Add the included reflections module.
     * Usually the simplest way to get going.
     */
    public static void addDefaultReflectionModule() {
        addModule(new ReflectionsModule());
    }
}
