package com.excercise.service;

//import com.oracle.oal.enotes.sc.extension.business.mapper.ContactSearchMapper;
//import com.oracle.oal.enotes.sc.extension.business.mapper.ContactSearchMapperImpl;
import com.excercise.resources.HelloWorld;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by dikushwa
 */
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        register(MultiPartFeature.class);
        register(JacksonFeature.class);

        // resources
        register(HelloWorld.class);
       

    }

//    protected AbstractBinder createBinder() {
//
//        return new AbstractBinder() {
//            @Override
//            protected void configure() {
//                bindFactory(SpikeCdiFactory.get(AccountRepository.class)).to(AccountRepository.class);
//                bindFactory(SpikeCdiFactory.get(ContactRepository.class)).to(ContactRepository.class);
//            }
//        };
//
//    }

}
