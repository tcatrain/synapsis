package org.synapsis.core.library.dao.impl;

import com.netflix.governator.annotations.AutoBindSingleton;
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
@AutoBindSingleton(baseClass = ILibraryDAO.class)
public class MemoryLibraryDAO implements ILibraryDAO {

    private static Map<UUID, Library> LIBRARY_MAP = new HashMap<>();

    @Override
    public UUID insertLibrary(Library _library) {
        UUID _id = UUID.randomUUID();
        _library.setId(_id);
        LIBRARY_MAP.put(_id, _library);
        return _id;
    }

    @Override
    public Library getLibrary(UUID _id) {
        return MemoryLibraryDAO.LIBRARY_MAP.get(_id);
    }

    @Override
    public Boolean updateLibrary(UUID _id, Library _library) {
        LIBRARY_MAP.replace(_id, _library);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteLibrary(UUID _id) {
        LIBRARY_MAP.remove(_id);
        return Boolean.TRUE;
    }

    @Override
    public Collection<Library> getAllLibraries() {
        return (LIBRARY_MAP.values());
    }
}
