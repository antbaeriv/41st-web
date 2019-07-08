package com.fortyfirst.orbat.service;

import com.fortyfirst.orbat.service.dto.TroopersDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.fortyfirst.orbat.domain.Troopers}.
 */
public interface TroopersService {

    /**
     * Save a troopers.
     *
     * @param troopersDTO the entity to save.
     * @return the persisted entity.
     */
    TroopersDTO save(TroopersDTO troopersDTO);

    /**
     * Get all the troopers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TroopersDTO> findAll(Pageable pageable);


    /**
     * Get the "id" troopers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TroopersDTO> findOne(Long id);

    /**
     * Delete the "id" troopers.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
