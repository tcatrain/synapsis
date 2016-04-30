package org.synapsis.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 30/04/2016
 * Author     : Thierry CATRAIN
 */
public class PropertyBundle {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyBundle.class);
    private static final String PROPERTY_BUNDLE_EXTENSION = ".properties";
    private HashMap<String, String> propertyBundleProperties;
    private String propertyBundleFilename;

    public PropertyBundle() {
        this.propertyBundleFilename = null;
        this.propertyBundleProperties = new HashMap<>();
    }

    public PropertyBundle(String _propertyBundle) {
        this.propertyBundleFilename = getFormattedPropertyBundleFilename(_propertyBundle);
        this.propertyBundleProperties = new HashMap<>();
    }

    public void setPropertyBundleFilename(String _propertyBundleFilename) {
        this.propertyBundleFilename = getFormattedPropertyBundleFilename(_propertyBundleFilename);;
    }

    public void load() {
        Properties properties = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(this.propertyBundleFilename);
        try {
            properties.load(inputStream);

            Enumeration<Object> propertyKeys = properties.keys();
            String key, value = null;
            while (propertyKeys.hasMoreElements()) {
                key = propertyKeys.nextElement().toString();
                value = properties.get(key).toString();
                this.propertyBundleProperties.put(key, value);
                LOGGER.info("Loading property with key \"{}\" and value \"{}\"", key, value);
            }
        } catch (IOException ioex) {
            LOGGER.error("Unable to load {} property bundle", this.propertyBundleFilename, ioex);
        }
    }

    public <T> T getProperty(String _propertyName, Class<T> klass) {
        if (!this.propertyBundleProperties.containsKey(_propertyName)) return null;
        T value = null;
        try {
            value = klass.getConstructor(String.class).newInstance(this.propertyBundleProperties.get(_propertyName));
        } catch (NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException ex) {
            LOGGER.error("Unable to get property with key \"{}\" and class \"{}\"", _propertyName, klass.getCanonicalName(), ex);
        }
        return value;
    }

    public static String getFormattedPropertyBundleFilename(String _propertyBundleName) {
        if (_propertyBundleName == null) return "";
        if (_propertyBundleName.endsWith(PROPERTY_BUNDLE_EXTENSION)) return _propertyBundleName.trim();
        return _propertyBundleName.trim().concat(PROPERTY_BUNDLE_EXTENSION);
    }

}
