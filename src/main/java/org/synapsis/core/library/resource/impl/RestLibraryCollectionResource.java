package org.synapsis.core.library.resource.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.synapsis.core.configuration.ConfigurationManager;
import org.synapsis.core.configuration.bundle.PropertyBundle;
import org.synapsis.core.library.business.ILibraryBusiness;
import org.synapsis.core.library.resource.ILibraryCollectionResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 18/04/2016
 * Author     : Thierry CATRAIN
 */
@Path("/api/libraries")
@Singleton()
public class RestLibraryCollectionResource implements ILibraryCollectionResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestLibraryCollectionResource.class);

    @Inject
    private ILibraryBusiness libraryBusiness;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        PropertyBundle propertyBundle = ConfigurationManager.getDefaultPropertyBundle();
        Integer intPort = propertyBundle.getProperty("jetty.http.port", Integer.class);
        LOGGER.info("Successfully retrieved Integer port {}", intPort);
        return Response.ok(this.libraryBusiness.readAllLibraries()).build();
    }

}
