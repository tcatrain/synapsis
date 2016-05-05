package org.synapsis.core.configuration.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.synapsis.core.configuration.bundle.PropertyBundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 01/05/2016
 * Author     : Thierry CATRAIN
 */
public class CacheFacade {

    private final static Logger LOGGER = LoggerFactory.getLogger(CacheFacade.class);
    private final Map<String, PropertyBundle> entries;

    public CacheFacade() {
        this.entries = new ConcurrentHashMap<>();
    }

    public <T extends Class> PropertyBundle getEntry(PropertyBundle _propertyBundle, T _klass) {
        if (_propertyBundle == null) return null;
        return this.getEntry(_propertyBundle.getPropertyBundleFilename(), _klass);
    }

    public <T extends Class> PropertyBundle getEntry(String _propertyBundleFilename, T _klass) {
        String key = PropertyBundle.getFormattedPropertyBundleFilename(_propertyBundleFilename);
        PropertyBundle propertyBundle = this.entries.get(key);
        if (propertyBundle == null) {
            propertyBundle = this.createNewPropertyBundleForClass(_propertyBundleFilename, _klass).load();
            this.entries.put(key, propertyBundle);
        }
        return propertyBundle;
    }

    @SuppressWarnings("unchecked")
    private <T extends Class> PropertyBundle createNewPropertyBundleForClass(String _propertyBundleFilename, T _klass) {
        PropertyBundle propertyBundle = null;
        try {
            Constructor constructor = _klass.getConstructor(String.class);
            propertyBundle = PropertyBundle.class.cast(constructor.newInstance(_propertyBundleFilename));
        } catch (NoSuchMethodException ex) {
            LOGGER.error("Could not find a valid String constructor for class \"{}\"", _klass.getCanonicalName(), ex);
        } catch (InstantiationException|IllegalAccessException|InvocationTargetException ex) {
            LOGGER.error("Could not create a new instance for class \"{}\"", _klass.getCanonicalName(), ex);
        }
        return propertyBundle;
    }
}
