/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.service;

/**
 *
 * @author dikushwa
 */
import javax.enterprise.context.RequestScoped;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.glassfish.hk2.api.Factory;

//HK2 binder factory for deltaspike managed CDI components
public class SpikeCdiFactory<T> implements Factory<T> {

    private ContextControl contextControl;
    private final Class<T> clazz;

    private SpikeCdiFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> SpikeCdiFactory<T> get(Class<T> clazz) {
        return new SpikeCdiFactory(clazz);
    }

    @Override
    public T provide() {
        contextControl = BeanProvider.getContextualReference(ContextControl.class);
        contextControl.startContext(RequestScoped.class);
        return BeanProvider.getContextualReference(clazz);
    }

    @Override
    public void dispose(T t) {
        contextControl.stopContext(RequestScoped.class);
    }
}
