package org.synapsis.core.library.server;

import com.google.inject.Guice;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.synapsis.core.configuration.ConfigurationManager;
import org.synapsis.core.configuration.PropertyBundle;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Properties;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public class SynapsisCoreServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynapsisCoreServer.class);

    private Server server;
    private String applicationId;
    private int port;

    private SynapsisCoreServer() {
        if (System.getenv("PORT") == null || System.getenv("PORT").isEmpty()) {
            this.port = 8080;
        } else {
            this.port = Integer.valueOf(System.getenv("PORT"));
        }
        this.server = new Server(this.port);
        this.applicationId = "synapsis-core";
        Guice.createInjector(new SynapsisCoreModule());
    }

    private void start()  throws Exception {
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        context.addServlet(DefaultServlet.class, "/*");
        server.start();
    }

    public static void main(String args[]) throws Exception {
        ConfigurationManager.setApplicationName("synapsis-core");
        PropertyBundle propertyBundle = ConfigurationManager.getDefaultPropertyBundle();
        Integer intPort = propertyBundle.getProperty("jetty.http.port", Integer.class);
        LOGGER.info("Successfully retrived Integer port {}", intPort);
        SynapsisCoreServer synapsisCoreServer = new SynapsisCoreServer();
        synapsisCoreServer.start();
    }
}
