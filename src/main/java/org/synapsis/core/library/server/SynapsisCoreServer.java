package org.synapsis.core.library.server;

import com.google.inject.Guice;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        this.port = 8080;
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
        SynapsisCoreServer synapsisCoreServer = new SynapsisCoreServer();
        Properties configProp = new Properties();
        configProp.load(SynapsisCoreServer.class.getClassLoader().getResourceAsStream(synapsisCoreServer.applicationId.concat(".properties")));
        System.out.println(configProp.getProperty("jersey.resources.package", "noJerseyResourcesPackageDefined"));
        synapsisCoreServer.start();
    }
}
