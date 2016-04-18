package org.synapsis.core.server;

import com.google.common.io.Closeables;
import com.google.inject.Injector;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.karyon.server.KaryonServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public abstract class ABaseServer implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ABaseServer.class);

    private final KaryonServer karyonServer;

    protected String serverHost;
    protected int serverPort;

    protected final Injector injector;

    protected ABaseServer() {
        this.karyonServer = new KaryonServer();
        this.injector = karyonServer.initialize();
    }

    public void start() {
        try {
            this.karyonServer.start();
        } catch (Exception exception) {
            throw new RuntimeException("Cannot start karyon server.", exception);
        }
    }

    @Override
    public void close() {
        try {
            Closeables.close(this.karyonServer, Boolean.FALSE);
        } catch (IOException exception) {
            LOGGER.error("Error shutting down karyon server.", exception);
        }
    }
}
