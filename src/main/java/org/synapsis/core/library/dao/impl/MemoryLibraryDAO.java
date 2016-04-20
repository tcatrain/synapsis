package org.synapsis.core.library.dao.impl;

import com.google.inject.Singleton;
import org.synapsis.core.library.dao.ILibraryDAO;
import org.synapsis.core.library.entity.Library;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
@Singleton()
public class MemoryLibraryDAO implements ILibraryDAO {

    private static Map<String, Library> LIBRARY_MAP = new HashMap<>();

    @Override
    public String insertLibrary(Library _library) {
        String _id = UUID.randomUUID().toString();
        _library.setId(_id);
        LIBRARY_MAP.put(_id, _library);
        return _id;
    }

    @Override
    public Library getLibrary(String _id) {
        return MemoryLibraryDAO.LIBRARY_MAP.get(_id);
    }

    @Override
    public Boolean updateLibrary(String _id, Library _library) {
        LIBRARY_MAP.replace(_id, _library);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteLibrary(String _id) {
        LIBRARY_MAP.remove(_id);
        return Boolean.TRUE;
    }

    @Override
    public Collection<Library> getAllLibraries() {
        return (LIBRARY_MAP.values());
    }
}
