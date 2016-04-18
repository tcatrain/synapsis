package org.synapsis.core.library.server;

import com.google.common.collect.Maps;
import com.netflix.governator.guice.LifecycleInjectorBuilder;
import com.netflix.karyon.server.ServerBootstrap;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.Map;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public class SynapsisCoreBootstrap extends ServerBootstrap {

    private static final String SYNAPSIS_CORE_BASE_PACKAGE = "org.synapsis.core";

    @Override
    protected void beforeInjectorCreation(LifecycleInjectorBuilder builderToBeUsed) {
        builderToBeUsed.withAdditionalModules(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                Map<String, String> params = Maps.newHashMap();
                params.put(PackagesResourceConfig.PROPERTY_PACKAGES, SYNAPSIS_CORE_BASE_PACKAGE);
                serve("/*").with(GuiceContainer.class, params);
                binder().bind(GuiceContainer.class).asEagerSingleton();
            }
        });
    }

}
