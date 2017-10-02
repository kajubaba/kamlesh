package com.narendra.sams.core.address.service.impl;

import com.narendra.sams.core.address.dao.AddressDAO;
import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.address.service.AddressService;
import java.util.List;

public class AddressServiceImpl implements AddressService {
    private AddressDAO addressDAO;

    public AddressDAO getAddressDAO() {
        return this.addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public State loadState(Long stateId) {
        return this.addressDAO.loadState(stateId);
    }

    public State getState(Long stateId) {
        return this.addressDAO.getState(stateId);
    }

    public Country getCountry(Long countryId) {
        return this.addressDAO.getCountry(countryId);
    }

    public Country loadCountry(Long countryId) {
        return this.addressDAO.loadCountry(countryId);
    }

    public List<Country> getAllContries() {
        return this.addressDAO.getAllContries();
    }

    public List<State> getAllStates() {
        return this.addressDAO.getAllStates();
    }
}
