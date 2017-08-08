package org.roelf.juicepress;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.roelf.juicepress.annotations.BindsTo;
import org.roelf.juicepress.annotations.InjectAssists;

import java.util.Set;

public class ReflectionsModule extends AbstractModule {
    private final Reflections reflections;

    public ReflectionsModule() {
        reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forJavaClassPath()));
    }

    public ReflectionsModule(String pkg) {
        reflections = new Reflections(pkg);
    }

    @Override
    protected void configure() {
        configureBindsTo();
        configureInjectAssists();
    }

    @SuppressWarnings("unchecked")
    private void configureBindsTo() {
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(BindsTo.class);

        annotatedClasses.forEach((Class<?> clazz) -> {
            Class<?> implementedClass = clazz.getAnnotation(BindsTo.class).value();

            if (implementedClass.isAssignableFrom(clazz))
                bind((Class)implementedClass).to(clazz);
            else
                throw new RuntimeException("Class " + clazz.getName() + " claims to implement " + implementedClass.getName() + ", which it doesn't.");
        });
    }

    @SuppressWarnings("unchecked")
    private void configureInjectAssists() {
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(InjectAssists.class);

        annotatedClasses.forEach((Class<?> clazz) -> {
            InjectAssists annotation = clazz.getAnnotation(InjectAssists.class);

            if (annotation.api().isAssignableFrom(annotation.implementation())) {

                install(new FactoryModuleBuilder()
                        .implement((Class)annotation.api(), annotation.implementation())
                        .build(clazz));

            } else {
                throw new RuntimeException("Class " + annotation.implementation().getName() + " claims to implement " + annotation.api().getName() + ", which it doesn't.");
            }
        });
    }
}
