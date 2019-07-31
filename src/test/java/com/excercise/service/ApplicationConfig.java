package com.excercise.service;

import com.excercise.repository.AccountRepository;
import com.excercise.repository.TransactionRepository;
import com.excercise.resources.AccountResource;
import com.excercise.resources.TransactionResource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author dikushwa
 */
public class ApplicationConfig extends ResourceConfig {
    
    public ApplicationConfig() {
        register(MultiPartFeature.class);
        register(JacksonFeature.class);

        // resources
        register(AccountResource.class);
        register(TransactionResource.class);
        register(createBinder());
        
    }

    protected AbstractBinder createBinder() {

        return new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(SpikeCdiFactory.get(AccountRepository.class)).to(AccountRepository.class);
                bindFactory(SpikeCdiFactory.get(TransactionRepository.class)).to(TransactionRepository.class);
                
            }
        };

    }

}
