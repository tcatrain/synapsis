package org.synapsis.core.configuration.caching;

import org.synapsis.core.configuration.bundle.PropertyBundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 01/05/2016
 * Author     : Thierry CATRAIN
 */
public class CacheManager {

    private Map<String, PropertyBundle> entries;

    public CacheManager() {
        this.entries = new HashMap<>();
    }

    public PropertyBundle getEntry(PropertyBundle _propertyBundle) {
        if (_propertyBundle == null) return null;
        return this.getEntry(_propertyBundle.getPropertyBundleFilename());
    }

    public PropertyBundle getEntry(String _propertyBundleFilename) {
        String key = PropertyBundle.getFormattedPropertyBundleFilename(_propertyBundleFilename);
        if (!this.entries.containsKey(key)) return null;
        return this.entries.get(key);
    }

    public PropertyBundle addEntry(String _propertyBundleFilename, PropertyBundle _propertyBundle) {
        String key = PropertyBundle.getFormattedPropertyBundleFilename(_propertyBundleFilename);
        if (!this.entries.containsKey(key)) this.entries.put(key, _propertyBundle);
        return this.entries.get(key);
    }
}
