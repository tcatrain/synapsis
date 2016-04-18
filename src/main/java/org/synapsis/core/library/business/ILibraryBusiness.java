package org.synapsis.core.library.business;

import org.synapsis.core.library.entity.Library;

import java.util.Collection;
import java.util.UUID;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public interface ILibraryBusiness {

    UUID createLibrary(Library _library);

    Library readLibrary(UUID _id);

    Boolean updateLibrary(UUID _id, Library _library);

    Boolean deleteLibrary(UUID _id);

    Collection<Library> readAllLibraries();

}
