package org.synapsis.core.library.resource.impl;

import com.google.inject.Inject;
import com.netflix.governator.annotations.AutoBindSingleton;
import com.sun.jersey.api.core.InjectParam;
import org.synapsis.core.library.business.ILibraryBusiness;
import org.synapsis.core.library.business.impl.DefaultLibraryBusiness;
import org.synapsis.core.library.entity.Library;
import org.synapsis.core.library.resource.ILibraryResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.UUID;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
@Path("/api/library")
@AutoBindSingleton(baseClass = ILibraryResource.class)
public class RestLibraryResource implements ILibraryResource {

    private final ILibraryBusiness libraryBusiness;

    @Inject
    public RestLibraryResource(@InjectParam DefaultLibraryBusiness _libraryBusiness) {
        this.libraryBusiness = _libraryBusiness;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public UUID create(Library _library) {
        return this.libraryBusiness.createLibrary(_library);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Library> list() {
        return this.libraryBusiness.readAllLibraries();
    }

    @GET
    @Path("/${id}")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public Library read(@PathParam("id") @Encoded UUID _id) {
        return this.libraryBusiness.readLibrary(_id);
    }

    @PUT
    @Path("/${id}")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public void update(@PathParam("id") UUID _id, Library _library) {
        this.libraryBusiness.updateLibrary(_id, _library);
    }

    @DELETE
    @Path("/${id}")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public void delete(@PathParam("id") UUID _id) {
        this.libraryBusiness.deleteLibrary(_id);
    }

}
