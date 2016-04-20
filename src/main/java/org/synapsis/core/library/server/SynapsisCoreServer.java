package org.synapsis.core.library.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import java.util.EnumSet;
import javax.servlet.DispatcherType;

import com.google.inject.Guice;
import com.google.inject.servlet.GuiceFilter;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public class SynapsisCoreServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynapsisCoreServer.class);

    private static Server server;
    private static int port;

    public SynapsisCoreServer() {
        this.port = 8080;
        this.server = new Server(this.port);
        Guice.createInjector(new SynapsisCoreModule());
    }

    public void start()  throws Exception {
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        context.addServlet(DefaultServlet.class, "/*");
        server.start();
    }

    public static void main(String args[]) throws Exception {
        SynapsisCoreServer synapsisCoreServer = new SynapsisCoreServer();
        synapsisCoreServer.start();
    }
}
