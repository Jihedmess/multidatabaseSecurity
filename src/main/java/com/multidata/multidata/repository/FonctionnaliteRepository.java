package com.multidata.multidata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multidata.multidata.models.Fonctionalite;


@Repository
public interface FonctionnaliteRepository extends JpaRepository<Fonctionalite,Long> {

}
