package com.example.trady.repository;

import com.example.trady.entity.Pcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcategoryRepository extends JpaRepository<Pcategory,Long> {

}
