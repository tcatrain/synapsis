package org.synapsis.core.server;

import com.netflix.config.DynamicPropertyFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public abstract class AJettyServer extends ABaseServer implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AJettyServer.class);

    private static final String JERSEY_RESOURCE_PACKAGE_PARAM = "com.sun.jersey.config.property.packages";
    private static final String JERSEY_RESOURCE_PACKAGE = "jersey.resources.package";
    private static final String JERSEY_RESOURCE_PACKAGE_DEFAULT = "No package jersey package defined";

    private static final String JERSEY_JSON_ENABLED_PARAM = "com.sun.jersey.api.json.POJOMappingFeature";
    private static final String JERSEY_JSON_ENABLED = "jersey.json.enabled";
    private static final boolean JERSEY_JSON_ENABLED_DEFAULT = true;

    private static final String JETTY_SERVER_PORT_PROPERTY = "jetty.http.port";


    private Server jettyServer;

    protected AJettyServer() {
        super();
    }

    @Override
    public void start() {
        super.start();

        this.serverPort = DynamicPropertyFactory.getInstance().getIntProperty(
                JETTY_SERVER_PORT_PROPERTY, Integer.MIN_VALUE
        ).get();
        this.serverHost = "localhost";
        this.jettyServer = new Server(this.serverPort);

        /* Jetty ContextHandler definition starts here */
        final Context context = new Context(jettyServer, "/", Context.SESSIONS);
        context.setResourceBase("webapp");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());

        Map<String, Object> packagesResourceConfigMap = new HashMap<>();
        packagesResourceConfigMap.put(JERSEY_RESOURCE_PACKAGE_PARAM,
                DynamicPropertyFactory.getInstance().getStringProperty(
                        JERSEY_RESOURCE_PACKAGE, JERSEY_RESOURCE_PACKAGE_DEFAULT).get()
        );
        packagesResourceConfigMap.put(JERSEY_JSON_ENABLED_PARAM,
                DynamicPropertyFactory.getInstance().getBooleanProperty(
                        JERSEY_JSON_ENABLED, JERSEY_JSON_ENABLED_DEFAULT).get()
        );
        final PackagesResourceConfig packagesResourceConfig = new PackagesResourceConfig(packagesResourceConfigMap);
        final ServletContainer servletContainer = new ServletContainer(packagesResourceConfig);
        context.addServlet(new ServletHolder(servletContainer), "/*");

        /* Jetty ContextHandler definition stops here */
        this.jettyServer.setHandler(context);

        try {
            this.jettyServer.start();
        } catch (Exception exception) {
            LOGGER.error("Error starting jetty server.", exception);
        }

        LOGGER.info("Successfully started jetty server at {}:{}", this.serverHost, this.serverPort);
    }

    @Override
    public void close() {
        try {
            this.jettyServer.stop();
        } catch (Exception exception) {
            LOGGER.error("Error shutting down jetty server.", exception);
        }
        super.close();
    }

}
