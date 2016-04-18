package org.synapsis.core.library.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

/**
 * Copyright 2016 (C) SYNAPSIS
 * <p>
 * Created on : 17/04/2016
 * Author     : Thierry CATRAIN
 */
@XmlRootElement
public class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private Integer addressNumber;
    private String addressStreet;
    private Integer addressZipCode;
    private String addressTown;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAdressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public Integer getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(Integer addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }
}
