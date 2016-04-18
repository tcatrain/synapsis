package org.synapsis.core.library.resource;

import com.google.inject.Inject;
import com.netflix.governator.annotations.AutoBindSingleton;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        GenericEntity<Collection<Library>> libraries = new GenericEntity<>(
                this.libraryBusiness.readAllLibraries(),
                Collection.class
        );
        return Response.ok(libraries).build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") String _id) {
        System.out.println("RestLibraryResource.read.start");
        Library library = this.libraryBusiness.readLibrary(_id);
        return Response.ok(library).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") String _id, Library _library) {
        this.libraryBusiness.updateLibrary(_id, _library);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") String _id) {
        this.libraryBusiness.deleteLibrary(_id);
    }

}
