package org.synapsis.core.library.resource;

import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import org.synapsis.core.library.entity.Library;

public interface ILibraryResource {

    public Response create(Library _library, UriInfo uriInfo);
    
    public Response read(String _id);
    
    public Response update(String _id, Library _library);
    
    public Response delete(String _id);
    
}