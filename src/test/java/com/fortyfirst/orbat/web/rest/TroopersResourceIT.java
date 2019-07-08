package com.fortyfirst.orbat.web.rest;

import com.fortyfirst.orbat.Application;
import com.fortyfirst.orbat.domain.Troopers;
import com.fortyfirst.orbat.repository.TroopersRepository;
import com.fortyfirst.orbat.service.TroopersService;
import com.fortyfirst.orbat.service.dto.TroopersDTO;
import com.fortyfirst.orbat.service.mapper.TroopersMapper;
import com.fortyfirst.orbat.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.fortyfirst.orbat.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link TroopersResource} REST controller.
 */
@SpringBootTest(classes = Application.class)
public class TroopersResourceIT {

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_RANGO = "AAAAAAAAAA";
    private static final String UPDATED_RANGO = "BBBBBBBBBB";

    private static final String DEFAULT_APODO = "AAAAAAAAAA";
    private static final String UPDATED_APODO = "BBBBBBBBBB";

    private static final Integer DEFAULT_ISACTIVE = 1;
    private static final Integer UPDATED_ISACTIVE = 2;

    private static final Integer DEFAULT_AMONESTACION = 1;
    private static final Integer UPDATED_AMONESTACION = 2;

    private static final String DEFAULT_ROL = "AAAAAAAAAA";
    private static final String UPDATED_ROL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_ENTRADA_SERVICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ENTRADA_SERVICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_ULTIMA_PROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ULTIMA_PROM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RECLUTADOR = "AAAAAAAAAA";
    private static final String UPDATED_RECLUTADOR = "BBBBBBBBBB";

    @Autowired
    private TroopersRepository troopersRepository;

    @Autowired
    private TroopersMapper troopersMapper;

    @Autowired
    private TroopersService troopersService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTroopersMockMvc;

    private Troopers troopers;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TroopersResource troopersResource = new TroopersResource(troopersService);
        this.restTroopersMockMvc = MockMvcBuilders.standaloneSetup(troopersResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Troopers createEntity(EntityManager em) {
        Troopers troopers = new Troopers()
            .numero(DEFAULT_NUMERO)
            .rango(DEFAULT_RANGO)
            .apodo(DEFAULT_APODO)
            .isactive(DEFAULT_ISACTIVE)
            .amonestacion(DEFAULT_AMONESTACION)
            .rol(DEFAULT_ROL)
            .fechaEntradaServicio(DEFAULT_FECHA_ENTRADA_SERVICIO)
            .fechaUltimaProm(DEFAULT_FECHA_ULTIMA_PROM)
            .reclutador(DEFAULT_RECLUTADOR);
        return troopers;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Troopers createUpdatedEntity(EntityManager em) {
        Troopers troopers = new Troopers()
            .numero(UPDATED_NUMERO)
            .rango(UPDATED_RANGO)
            .apodo(UPDATED_APODO)
            .isactive(UPDATED_ISACTIVE)
            .amonestacion(UPDATED_AMONESTACION)
            .rol(UPDATED_ROL)
            .fechaEntradaServicio(UPDATED_FECHA_ENTRADA_SERVICIO)
            .fechaUltimaProm(UPDATED_FECHA_ULTIMA_PROM)
            .reclutador(UPDATED_RECLUTADOR);
        return troopers;
    }

    @BeforeEach
    public void initTest() {
        troopers = createEntity(em);
    }

    @Test
    @Transactional
    public void createTroopers() throws Exception {
        int databaseSizeBeforeCreate = troopersRepository.findAll().size();

        // Create the Troopers
        TroopersDTO troopersDTO = troopersMapper.toDto(troopers);
        restTroopersMockMvc.perform(post("/api/troopers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(troopersDTO)))
            .andExpect(status().isCreated());

        // Validate the Troopers in the database
        List<Troopers> troopersList = troopersRepository.findAll();
        assertThat(troopersList).hasSize(databaseSizeBeforeCreate + 1);
        Troopers testTroopers = troopersList.get(troopersList.size() - 1);
        assertThat(testTroopers.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testTroopers.getRango()).isEqualTo(DEFAULT_RANGO);
        assertThat(testTroopers.getApodo()).isEqualTo(DEFAULT_APODO);
        assertThat(testTroopers.getIsactive()).isEqualTo(DEFAULT_ISACTIVE);
        assertThat(testTroopers.getAmonestacion()).isEqualTo(DEFAULT_AMONESTACION);
        assertThat(testTroopers.getRol()).isEqualTo(DEFAULT_ROL);
        assertThat(testTroopers.getFechaEntradaServicio()).isEqualTo(DEFAULT_FECHA_ENTRADA_SERVICIO);
        assertThat(testTroopers.getFechaUltimaProm()).isEqualTo(DEFAULT_FECHA_ULTIMA_PROM);
        assertThat(testTroopers.getReclutador()).isEqualTo(DEFAULT_RECLUTADOR);
    }

    @Test
    @Transactional
    public void createTroopersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = troopersRepository.findAll().size();

        // Create the Troopers with an existing ID
        troopers.setId(1L);
        TroopersDTO troopersDTO = troopersMapper.toDto(troopers);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTroopersMockMvc.perform(post("/api/troopers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(troopersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Troopers in the database
        List<Troopers> troopersList = troopersRepository.findAll();
        assertThat(troopersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTroopers() throws Exception {
        // Initialize the database
        troopersRepository.saveAndFlush(troopers);

        // Get all the troopersList
        restTroopersMockMvc.perform(get("/api/troopers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(troopers.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].rango").value(hasItem(DEFAULT_RANGO.toString())))
            .andExpect(jsonPath("$.[*].apodo").value(hasItem(DEFAULT_APODO.toString())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE)))
            .andExpect(jsonPath("$.[*].amonestacion").value(hasItem(DEFAULT_AMONESTACION)))
            .andExpect(jsonPath("$.[*].rol").value(hasItem(DEFAULT_ROL.toString())))
            .andExpect(jsonPath("$.[*].fechaEntradaServicio").value(hasItem(DEFAULT_FECHA_ENTRADA_SERVICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaUltimaProm").value(hasItem(DEFAULT_FECHA_ULTIMA_PROM.toString())))
            .andExpect(jsonPath("$.[*].reclutador").value(hasItem(DEFAULT_RECLUTADOR.toString())));
    }
    
    @Test
    @Transactional
    public void getTroopers() throws Exception {
        // Initialize the database
        troopersRepository.saveAndFlush(troopers);

        // Get the troopers
        restTroopersMockMvc.perform(get("/api/troopers/{id}", troopers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(troopers.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.rango").value(DEFAULT_RANGO.toString()))
            .andExpect(jsonPath("$.apodo").value(DEFAULT_APODO.toString()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE))
            .andExpect(jsonPath("$.amonestacion").value(DEFAULT_AMONESTACION))
            .andExpect(jsonPath("$.rol").value(DEFAULT_ROL.toString()))
            .andExpect(jsonPath("$.fechaEntradaServicio").value(DEFAULT_FECHA_ENTRADA_SERVICIO.toString()))
            .andExpect(jsonPath("$.fechaUltimaProm").value(DEFAULT_FECHA_ULTIMA_PROM.toString()))
            .andExpect(jsonPath("$.reclutador").value(DEFAULT_RECLUTADOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTroopers() throws Exception {
        // Get the troopers
        restTroopersMockMvc.perform(get("/api/troopers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTroopers() throws Exception {
        // Initialize the database
        troopersRepository.saveAndFlush(troopers);

        int databaseSizeBeforeUpdate = troopersRepository.findAll().size();

        // Update the troopers
        Troopers updatedTroopers = troopersRepository.findById(troopers.getId()).get();
        // Disconnect from session so that the updates on updatedTroopers are not directly saved in db
        em.detach(updatedTroopers);
        updatedTroopers
            .numero(UPDATED_NUMERO)
            .rango(UPDATED_RANGO)
            .apodo(UPDATED_APODO)
            .isactive(UPDATED_ISACTIVE)
            .amonestacion(UPDATED_AMONESTACION)
            .rol(UPDATED_ROL)
            .fechaEntradaServicio(UPDATED_FECHA_ENTRADA_SERVICIO)
            .fechaUltimaProm(UPDATED_FECHA_ULTIMA_PROM)
            .reclutador(UPDATED_RECLUTADOR);
        TroopersDTO troopersDTO = troopersMapper.toDto(updatedTroopers);

        restTroopersMockMvc.perform(put("/api/troopers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(troopersDTO)))
            .andExpect(status().isOk());

        // Validate the Troopers in the database
        List<Troopers> troopersList = troopersRepository.findAll();
        assertThat(troopersList).hasSize(databaseSizeBeforeUpdate);
        Troopers testTroopers = troopersList.get(troopersList.size() - 1);
        assertThat(testTroopers.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTroopers.getRango()).isEqualTo(UPDATED_RANGO);
        assertThat(testTroopers.getApodo()).isEqualTo(UPDATED_APODO);
        assertThat(testTroopers.getIsactive()).isEqualTo(UPDATED_ISACTIVE);
        assertThat(testTroopers.getAmonestacion()).isEqualTo(UPDATED_AMONESTACION);
        assertThat(testTroopers.getRol()).isEqualTo(UPDATED_ROL);
        assertThat(testTroopers.getFechaEntradaServicio()).isEqualTo(UPDATED_FECHA_ENTRADA_SERVICIO);
        assertThat(testTroopers.getFechaUltimaProm()).isEqualTo(UPDATED_FECHA_ULTIMA_PROM);
        assertThat(testTroopers.getReclutador()).isEqualTo(UPDATED_RECLUTADOR);
    }

    @Test
    @Transactional
    public void updateNonExistingTroopers() throws Exception {
        int databaseSizeBeforeUpdate = troopersRepository.findAll().size();

        // Create the Troopers
        TroopersDTO troopersDTO = troopersMapper.toDto(troopers);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTroopersMockMvc.perform(put("/api/troopers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(troopersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Troopers in the database
        List<Troopers> troopersList = troopersRepository.findAll();
        assertThat(troopersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTroopers() throws Exception {
        // Initialize the database
        troopersRepository.saveAndFlush(troopers);

        int databaseSizeBeforeDelete = troopersRepository.findAll().size();

        // Delete the troopers
        restTroopersMockMvc.perform(delete("/api/troopers/{id}", troopers.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Troopers> troopersList = troopersRepository.findAll();
        assertThat(troopersList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Troopers.class);
        Troopers troopers1 = new Troopers();
        troopers1.setId(1L);
        Troopers troopers2 = new Troopers();
        troopers2.setId(troopers1.getId());
        assertThat(troopers1).isEqualTo(troopers2);
        troopers2.setId(2L);
        assertThat(troopers1).isNotEqualTo(troopers2);
        troopers1.setId(null);
        assertThat(troopers1).isNotEqualTo(troopers2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TroopersDTO.class);
        TroopersDTO troopersDTO1 = new TroopersDTO();
        troopersDTO1.setId(1L);
        TroopersDTO troopersDTO2 = new TroopersDTO();
        assertThat(troopersDTO1).isNotEqualTo(troopersDTO2);
        troopersDTO2.setId(troopersDTO1.getId());
        assertThat(troopersDTO1).isEqualTo(troopersDTO2);
        troopersDTO2.setId(2L);
        assertThat(troopersDTO1).isNotEqualTo(troopersDTO2);
        troopersDTO1.setId(null);
        assertThat(troopersDTO1).isNotEqualTo(troopersDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(troopersMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(troopersMapper.fromId(null)).isNull();
    }
}
