package com.putatu.repository;

import com.putatu.domain.Staff;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Staff entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
