package org.synapsis.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.synapsis.core.configuration.bundle.PropertyBundle;
import org.synapsis.core.configuration.bundle.UpdatablePropertyBundle;
import org.synapsis.core.configuration.caching.CacheFacade;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 30/04/2016
 * Author     : Thierry CATRAIN
 */
public class ConfigurationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationManager.class);
    private static final CacheFacade CACHE_FACADE = new CacheFacade();
    private static String APPLICATION_NAME = null;

    public static synchronized void setApplicationName(String _applicationName) {
        ConfigurationManager.APPLICATION_NAME = _applicationName;
    }

    public static PropertyBundle getDefaultPropertyBundle() {
        return ConfigurationManager.getPropertyBundle(APPLICATION_NAME);
    }

    public static PropertyBundle getDefaultUpdatablePropertyBundle() {
        return ConfigurationManager.getUpdatablePropertyBundle(APPLICATION_NAME);
    }

    public static PropertyBundle getPropertyBundle(String _propertyBundleFilename) {
        return CACHE_FACADE.getEntry(_propertyBundleFilename, PropertyBundle.class);
    }

    public static PropertyBundle getUpdatablePropertyBundle(String _updatablePropertyBundleFilename) {
        return CACHE_FACADE.getEntry(_updatablePropertyBundleFilename, UpdatablePropertyBundle.class);
    }
}
