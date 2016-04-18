package org.synapsis.core.library.business.impl;

import com.google.inject.Inject;
import com.netflix.governator.annotations.AutoBindSingleton;
import com.sun.jersey.api.core.InjectParam;
import org.synapsis.core.library.business.ILibraryBusiness;
import org.synapsis.core.library.dao.ILibraryDAO;
import org.synapsis.core.library.dao.impl.MemoryLibraryDAO;
import org.synapsis.core.library.entity.Library;

import java.util.Collection;
import java.util.UUID;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
@AutoBindSingleton(baseClass = ILibraryBusiness.class)
public class DefaultLibraryBusiness implements ILibraryBusiness {

    private final ILibraryDAO libraryDAO;

    @Inject
    public DefaultLibraryBusiness(@InjectParam MemoryLibraryDAO _libraryDAO) {
        this.libraryDAO = _libraryDAO;
    }

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
