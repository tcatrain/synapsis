package org.synapsis.core.library.resource.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.synapsis.core.library.business.ILibraryBusiness;
import org.synapsis.core.library.business.impl.DefaultLibraryBusiness;
import org.synapsis.core.library.entity.Library;
import org.synapsis.core.library.resource.ILibraryResource;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;
import java.util.UUID;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
@Path("/api/library")
@Singleton()
public class RestLibraryResource implements ILibraryResource {

    @Inject
    private ILibraryBusiness libraryBusiness;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Library _library, @Context UriInfo uriInfo) {
        UUID id = this.libraryBusiness.createLibrary(_library);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(id.toString());
        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") UUID _id) {
        return Response.ok(this.libraryBusiness.readLibrary(_id)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") UUID _id, Library _library) {
        this.libraryBusiness.updateLibrary(_id, _library);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") UUID _id) {
        this.libraryBusiness.deleteLibrary(_id);
        return Response.ok().build();
    }

}
