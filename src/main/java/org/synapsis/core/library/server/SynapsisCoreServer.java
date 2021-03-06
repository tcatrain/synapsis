package org.synapsis.core.library.server;

import com.google.inject.Guice;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.synapsis.core.configuration.ConfigurationManager;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public class SynapsisCoreServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynapsisCoreServer.class);

    private final Server server;
    private final static String APPLICATION_ID = "synapsis-core";

    private SynapsisCoreServer() {
        int port;
        if (System.getenv("PORT") == null || System.getenv("PORT").isEmpty()) {
            port = 8080;
        } else {
            port = Integer.valueOf(System.getenv("PORT"));
        }
        this.server = new Server(port);
        Guice.createInjector(new SynapsisCoreModule());
    }

    private void start()  throws Exception {
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        context.addServlet(DefaultServlet.class, "/*");
        server.start();
    }

    public static void main(String args[]) throws Exception {
        ConfigurationManager.setApplicationName(APPLICATION_ID);
        SynapsisCoreServer synapsisCoreServer = new SynapsisCoreServer();
        synapsisCoreServer.start();
    }
}
