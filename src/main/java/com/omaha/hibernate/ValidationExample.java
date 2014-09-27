package com.omaha.hibernate;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.aopalliance.intercept.Joinpoint;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.method.MethodValidator;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import static com.google.inject.matcher.Matchers.any;

public class ValidationExample {
    private final Injector injector = Guice.createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            bindInterceptor(any(), any(), Joinpoint::proceed);
            bind(Resource.class).asEagerSingleton();
        }
    });

    private Validator getValidator() {
        return Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory().getValidator();
    }

    public Boolean validate(Object o, Method method, Object[] args) {
        return getValidator().unwrap(MethodValidator.class).validateAllParameters(o, method, args).isEmpty();
    }

    public Boolean listOfEntities() {
        try {
            return validate(
                    injector.getInstance(Resource.class),
                    Resource.class.getMethod("validatedMethodWithListOfEntities", String.class, List.class),
                    new Object[] { "asdf", Collections.singleton(new Entity("")) });
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean listOfPrimitives() {
        try {
            return validate(
                    injector.getInstance(Resource.class),
                    Resource.class.getMethod("validatedMethodWithListOfPrimitives", String.class, List.class),
                    new Object[] { "", Collections.emptyList() });
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
