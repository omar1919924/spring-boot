package com.covoiturage.repository;

import com.covoiturage.entity.Passager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassagerRepository extends JpaRepository<Passager,Long>{

}
