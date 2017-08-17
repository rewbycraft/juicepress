package org.roelf.juicepress;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import org.roelf.juicepress.annotations.BindsTo;
import org.roelf.juicepress.annotations.InjectAssists;
import org.roelf.juicepress.annotations.MultiBindsTo;
import org.roelf.juicepress.annotations.ProvidesType;

import java.lang.annotation.Annotation;
import java.util.Set;

public abstract class AnnotationScannerModule extends AbstractModule {
    protected abstract Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation);

    @Override
    protected void configure() {
        configureBindsTo();
        configureMultiBindsTo();
        configureInjectAssists();
        configureProviders();
    }

    @SuppressWarnings("unchecked")
    private void configureBindsTo() {
        Set<Class<?>> annotatedClasses = getTypesAnnotatedWith(BindsTo.class);

        annotatedClasses.forEach((Class<?> clazz) -> {
            Class<?> implementedClass = clazz.getAnnotation(BindsTo.class).value();

            if (implementedClass.isAssignableFrom(clazz))
                bind((Class) implementedClass).to(clazz);
            else
                throw new RuntimeException("Class " + clazz.getName() + " claims to implement " + implementedClass.getName() + ", which it doesn't.");
        });
    }

    @SuppressWarnings("unchecked")
    private void configureMultiBindsTo() {
        Set<Class<?>> annotatedClasses = getTypesAnnotatedWith(MultiBindsTo.class);

        annotatedClasses.forEach((Class<?> clazz) -> {
            Class<?> implementedClass = clazz.getAnnotation(MultiBindsTo.class).value();

            if (implementedClass.isAssignableFrom(clazz)) {
                Multibinder.newSetBinder(binder(), (Class)implementedClass).addBinding().to(clazz);
            }else
                throw new RuntimeException("Class " + clazz.getName() + " claims to implement " + implementedClass.getName() + ", which it doesn't.");
        });
    }

    @SuppressWarnings("unchecked")
    private void configureInjectAssists() {
        Set<Class<?>> annotatedClasses = getTypesAnnotatedWith(InjectAssists.class);

        annotatedClasses.forEach((Class<?> clazz) -> {
            InjectAssists annotation = clazz.getAnnotation(InjectAssists.class);

            if (annotation.api().isAssignableFrom(annotation.implementation())) {

                install(new FactoryModuleBuilder()
                        .implement((Class) annotation.api(), annotation.implementation())
                        .build(clazz));

            } else {
                throw new RuntimeException("Class " + annotation.implementation().getName() + " claims to implement " + annotation.api().getName() + ", which it doesn't.");
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void configureProviders() {
        Set<Class<?>> annotatedClasses = getTypesAnnotatedWith(ProvidesType.class);

        annotatedClasses.forEach((Class<?> clazz) -> {
            ProvidesType annotation = clazz.getAnnotation(ProvidesType.class);

            if (Provider.class.isAssignableFrom(clazz)) {
                bind(annotation.value()).toProvider((Class)clazz);
            } else {
                throw new RuntimeException("Class " + annotation.value().getName() + " claims to implement " + Provider.class.getName() + ", which it doesn't.");
            }
        });
    }
}
