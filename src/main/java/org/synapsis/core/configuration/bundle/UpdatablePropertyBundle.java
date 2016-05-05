package org.synapsis.core.configuration.bundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 30/04/2016
 * Author     : Thierry CATRAIN
 */
public class UpdatablePropertyBundle extends PropertyBundle {

    private final static Logger LOGGER = LoggerFactory.getLogger(UpdatablePropertyBundle.class);
    private long lastModified;

    protected UpdatablePropertyBundle() {
        super();
    }

    public UpdatablePropertyBundle(String _propertyBundleFilename) {
        super(_propertyBundleFilename);
    }

    @Override
    public PropertyBundle load() {
        PropertyBundle propertyBundle = super.load();
        this.lastModified = this.getFileLastModified();
        return propertyBundle;
    }

    @Override
    public <T> T getProperty(String _propertyName, Class<T> klass) {
        if (this.wasUpdated()) {
            LOGGER.info("Modification detected for resource \"{}\"", this.resourceUrl);
            this.load();
        }
        return super.getProperty(_propertyName, klass);
    }

    private Boolean wasUpdated() {
        long fileLastModified = this.getFileLastModified();
        if (fileLastModified > this.lastModified) {
            this.lastModified = fileLastModified;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private long getFileLastModified() {
        String resourceFilePath = this.resourceUrl.getFile();
        File file = new File(resourceFilePath);
        return file.lastModified();
    }
}
