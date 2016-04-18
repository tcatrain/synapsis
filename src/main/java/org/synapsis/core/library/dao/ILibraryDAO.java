package org.synapsis.core.library.dao;

import org.synapsis.core.library.entity.Library;

import java.util.Collection;
import java.util.UUID;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
public interface ILibraryDAO {

    String insertLibrary(Library _library);

    Library getLibrary(String _id);

    Boolean updateLibrary(String _id, Library _library);

    Boolean deleteLibrary(String _id);

    Collection<Library> getAllLibraries();

}
