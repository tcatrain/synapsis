package org.synapsis.core.library.resource;

import com.google.inject.Inject;
import com.sun.jersey.api.core.InjectParam;
import org.synapsis.core.library.business.ILibraryBusiness;
import org.synapsis.core.library.business.impl.DefaultLibraryBusiness;

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
public class RestLibraryCollectionResource {

    private final ILibraryBusiness libraryBusiness;

    @Inject
    public RestLibraryCollectionResource(@InjectParam DefaultLibraryBusiness _libraryBusiness) {
        this.libraryBusiness = _libraryBusiness;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(this.libraryBusiness.readAllLibraries()).build();
    }

}
