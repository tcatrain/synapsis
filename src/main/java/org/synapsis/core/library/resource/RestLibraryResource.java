package org.synapsis.core.library.resource;

import com.google.inject.Inject;
import com.sun.jersey.api.core.InjectParam;
import org.synapsis.core.library.business.ILibraryBusiness;
import org.synapsis.core.library.business.impl.DefaultLibraryBusiness;
import org.synapsis.core.library.entity.Library;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
@Path("/api/library")
public class RestLibraryResource {

    private final ILibraryBusiness libraryBusiness;

    @Inject
    public RestLibraryResource(@InjectParam DefaultLibraryBusiness _libraryBusiness) {
        this.libraryBusiness = _libraryBusiness;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Library _library, @Context UriInfo uriInfo) {
        String id = this.libraryBusiness.createLibrary(_library);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(id);
        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") String _id) {
        return Response.ok(this.libraryBusiness.readLibrary(_id)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String _id, Library _library) {
        this.libraryBusiness.updateLibrary(_id, _library);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String _id) {
        this.libraryBusiness.deleteLibrary(_id);
        return Response.ok().build();
    }

}
