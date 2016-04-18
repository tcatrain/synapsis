package org.synapsis.core.library.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.synapsis.core.server.AJettyServer;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public class SynapsisCoreServer extends AJettyServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynapsisCoreServer.class);

    public SynapsisCoreServer() {
    }

    @Override
    public void start() {
        super.start();
    }

    public static void main(String args[]) {
        System.setProperty("archaius.deployment.applicationId", "synapsis-core");
        SynapsisCoreServer synapsisCoreServer = new SynapsisCoreServer();
        synapsisCoreServer.start();
    }
}
