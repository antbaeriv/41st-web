package com.fortyfirst.orbat.service.impl;

import com.fortyfirst.orbat.service.TroopersService;
import com.fortyfirst.orbat.domain.Troopers;
import com.fortyfirst.orbat.repository.TroopersRepository;
import com.fortyfirst.orbat.service.dto.TroopersDTO;
import com.fortyfirst.orbat.service.mapper.TroopersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Troopers}.
 */
@Service
@Transactional
public class TroopersServiceImpl implements TroopersService {

    private final Logger log = LoggerFactory.getLogger(TroopersServiceImpl.class);

    private final TroopersRepository troopersRepository;

    private final TroopersMapper troopersMapper;

    public TroopersServiceImpl(TroopersRepository troopersRepository, TroopersMapper troopersMapper) {
        this.troopersRepository = troopersRepository;
        this.troopersMapper = troopersMapper;
    }

    /**
     * Save a troopers.
     *
     * @param troopersDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TroopersDTO save(TroopersDTO troopersDTO) {
        log.debug("Request to save Troopers : {}", troopersDTO);
        Troopers troopers = troopersMapper.toEntity(troopersDTO);
        troopers = troopersRepository.save(troopers);
        return troopersMapper.toDto(troopers);
    }

    /**
     * Get all the troopers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TroopersDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Troopers");
        return troopersRepository.findAll(pageable)
            .map(troopersMapper::toDto);
    }


    /**
     * Get one troopers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TroopersDTO> findOne(Long id) {
        log.debug("Request to get Troopers : {}", id);
        return troopersRepository.findById(id)
            .map(troopersMapper::toDto);
    }

    /**
     * Delete the troopers by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Troopers : {}", id);
        troopersRepository.deleteById(id);
    }
}
