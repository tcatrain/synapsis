package org.synapsis.core.library.server;

import com.netflix.karyon.spi.HealthCheckHandler;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public class SynapsisCoreHealthChecker implements HealthCheckHandler {

    @Override
    public int getStatus() {
        return 200;
    }

}
