package com.narendra.sams.core.address.dao;

import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import java.util.List;

public interface AddressDAO {
    List<Country> getAllContries();

    List<State> getAllStates();

    Country getCountry(Long l);

    State getState(Long l);

    Country loadCountry(Long l);

    State loadState(Long l);
}
