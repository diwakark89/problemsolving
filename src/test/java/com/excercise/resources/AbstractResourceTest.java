package com.excercise.resources;

import com.excercise.service.SetupTestData;
import javax.ws.rs.core.Application;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 * Created by dikushwa.
 */
public abstract class AbstractResourceTest extends JerseyTest {

    protected final static String RESOURCE_PATH = "";
    private long startTime;
    private SetupTestData setup=null;

    @Rule
    public TestName testName = new TestName();

    private static CdiContainer cdiContainer;

    public AbstractResourceTest() {
//        System.setProperty("logback.LogPath", "target");
//
//
//
//        enable(TestProperties.LOG_TRAFFIC);
//        enable(TestProperties.DUMP_ENTITY);
    }

    @BeforeClass
    public static void startCdiContainer() {
        cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();
        ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContexts();
        
        
    }

    @AfterClass
    public static void stopCdiContainer() {
        ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.stopContexts();
        cdiContainer.shutdown();
    }

    @Before
    public void before() {
        System.out.println("-- ----------------------------");
        System.out.println("-- " + testName.getMethodName());
        startTime = System.currentTimeMillis();
//        setup=new SetupTestData();
//        setup.load();
    }
    
    @After
    public void after(){
        System.out.println("--- "+testName.getMethodName()+" time taken:"+(System.currentTimeMillis()-startTime)+"ms");
    }

    @Override
    protected abstract Application configure();

}
