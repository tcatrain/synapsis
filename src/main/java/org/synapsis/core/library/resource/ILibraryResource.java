package org.synapsis.core.library.resource;

import org.synapsis.core.library.entity.Library;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

public interface ILibraryResource {

    public Response create(Library _library, UriInfo uriInfo);

    public Response read(UUID _id);

    public Response update(UUID _id, Library _library);

    public Response delete(UUID _id);

}
