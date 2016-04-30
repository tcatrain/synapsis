package org.synapsis.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 30/04/2016
 * Author     : Thierry CATRAIN
 */
public class ConfigurationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationManager.class);
    private static String APPLICATION_NAME = null;

    public static synchronized void setApplicationName(String _applicationName) {
        ConfigurationManager.APPLICATION_NAME = _applicationName;
    }

    public static PropertyBundle getDefaultPropertyBundle() {
        return ConfigurationManager.getPropertyBundle(APPLICATION_NAME);
    }

    public static UpdatablePropertyBundle getDefaultUpdatablePropertyBundle() {
        return ConfigurationManager.getUpdatablePropertyBundle(APPLICATION_NAME);
    }

    public static PropertyBundle getPropertyBundle(String _propertyBundle) {
        PropertyBundle propertyBundle = new PropertyBundle(_propertyBundle);
        propertyBundle.load();
        return propertyBundle;
    }

    public static UpdatablePropertyBundle getUpdatablePropertyBundle(String _updatablePropertyBundle) {
        UpdatablePropertyBundle propertyBundle = new UpdatablePropertyBundle(_updatablePropertyBundle);
        return null;
    }
}
