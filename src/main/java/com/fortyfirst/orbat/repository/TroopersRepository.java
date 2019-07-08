package com.fortyfirst.orbat.repository;

import com.fortyfirst.orbat.domain.Troopers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Troopers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TroopersRepository extends JpaRepository<Troopers, Long> {

}
