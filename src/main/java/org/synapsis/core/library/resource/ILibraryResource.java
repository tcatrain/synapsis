package org.synapsis.core.library.resource;

import org.synapsis.core.library.entity.Library;

import java.util.UUID;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public interface ILibraryResource {

    UUID create(Library _library);

    Library read(UUID _id);

    void update(UUID _id, Library _library);

    void delete(UUID _id);

}
