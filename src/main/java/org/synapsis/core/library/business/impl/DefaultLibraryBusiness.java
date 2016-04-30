package org.synapsis.core.library.business.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.synapsis.core.library.business.ILibraryBusiness;
import org.synapsis.core.library.dao.ILibraryDAO;
import org.synapsis.core.library.entity.Library;

import java.util.Collection;
import java.util.UUID;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
@Singleton()
public class DefaultLibraryBusiness implements ILibraryBusiness {

    @Inject
    private ILibraryDAO libraryDAO;

    @Override
    public UUID createLibrary(Library _library) {
        return this.libraryDAO.insertLibrary(_library);
    }

    @Override
    public Library readLibrary(UUID _id) {
        return this.libraryDAO.getLibrary(_id);
    }

    @Override
    public Boolean updateLibrary(UUID _id, Library _library) {
        return this.libraryDAO.updateLibrary(_id, _library);
    }

    @Override
    public Boolean deleteLibrary(UUID _id) {
        return this.libraryDAO.deleteLibrary(_id);
    }

    @Override
    public Collection<Library> readAllLibraries() { return this.libraryDAO.getAllLibraries(); }

}
