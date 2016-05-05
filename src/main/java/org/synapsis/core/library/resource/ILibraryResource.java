package org.synapsis.core.library.resource;

import org.synapsis.core.library.entity.Library;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

public interface ILibraryResource {

    Response create(Library _library, UriInfo uriInfo);

    Response read(UUID _id);

    Response update(UUID _id, Library _library);

    Response delete(UUID _id);

}
