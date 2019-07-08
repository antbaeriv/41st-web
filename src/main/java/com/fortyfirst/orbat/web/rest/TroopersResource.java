package com.fortyfirst.orbat.web.rest;

import com.fortyfirst.orbat.service.TroopersService;
import com.fortyfirst.orbat.web.rest.errors.BadRequestAlertException;
import com.fortyfirst.orbat.service.dto.TroopersDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.fortyfirst.orbat.domain.Troopers}.
 */
@RestController
@RequestMapping("/api")
public class TroopersResource {

    private final Logger log = LoggerFactory.getLogger(TroopersResource.class);

    private static final String ENTITY_NAME = "troopers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TroopersService troopersService;

    public TroopersResource(TroopersService troopersService) {
        this.troopersService = troopersService;
    }

    /**
     * {@code POST  /troopers} : Create a new troopers.
     *
     * @param troopersDTO the troopersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new troopersDTO, or with status {@code 400 (Bad Request)} if the troopers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/troopers")
    public ResponseEntity<TroopersDTO> createTroopers(@RequestBody TroopersDTO troopersDTO) throws URISyntaxException {
        log.debug("REST request to save Troopers : {}", troopersDTO);
        if (troopersDTO.getId() != null) {
            throw new BadRequestAlertException("A new troopers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TroopersDTO result = troopersService.save(troopersDTO);
        return ResponseEntity.created(new URI("/api/troopers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /troopers} : Updates an existing troopers.
     *
     * @param troopersDTO the troopersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated troopersDTO,
     * or with status {@code 400 (Bad Request)} if the troopersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the troopersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/troopers")
    public ResponseEntity<TroopersDTO> updateTroopers(@RequestBody TroopersDTO troopersDTO) throws URISyntaxException {
        log.debug("REST request to update Troopers : {}", troopersDTO);
        if (troopersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TroopersDTO result = troopersService.save(troopersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, troopersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /troopers} : get all the troopers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of troopers in body.
     */
    @GetMapping("/troopers")
    public ResponseEntity<List<TroopersDTO>> getAllTroopers(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Troopers");
        Page<TroopersDTO> page = troopersService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /troopers/:id} : get the "id" troopers.
     *
     * @param id the id of the troopersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the troopersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/troopers/{id}")
    public ResponseEntity<TroopersDTO> getTroopers(@PathVariable Long id) {
        log.debug("REST request to get Troopers : {}", id);
        Optional<TroopersDTO> troopersDTO = troopersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(troopersDTO);
    }

    /**
     * {@code DELETE  /troopers/:id} : delete the "id" troopers.
     *
     * @param id the id of the troopersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/troopers/{id}")
    public ResponseEntity<Void> deleteTroopers(@PathVariable Long id) {
        log.debug("REST request to delete Troopers : {}", id);
        troopersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
