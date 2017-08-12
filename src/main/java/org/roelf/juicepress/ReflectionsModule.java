package org.roelf.juicepress;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ReflectionsModule extends AnnotationScannerModule {
    private final Reflections reflections;

    public ReflectionsModule() {
        reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forJavaClassPath()));
    }

    public ReflectionsModule(String pkg) {
        reflections = new Reflections(pkg);
    }


    @Override
    protected Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation) {
        return reflections.getTypesAnnotatedWith(annotation);
    }
}
