/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.service;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 *
 * @author dikushwa
 */
public abstract class BaseTest  {


    private static CdiContainer cdiContainer;

    @Rule
    public TestName name = new TestName();

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


}
