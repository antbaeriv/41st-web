package com.fortyfirst.orbat.service.mapper;

import com.fortyfirst.orbat.domain.*;
import com.fortyfirst.orbat.service.dto.TroopersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Troopers} and its DTO {@link TroopersDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TroopersMapper extends EntityMapper<TroopersDTO, Troopers> {



    default Troopers fromId(Long id) {
        if (id == null) {
            return null;
        }
        Troopers troopers = new Troopers();
        troopers.setId(id);
        return troopers;
    }
}
