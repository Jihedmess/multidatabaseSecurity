package com.multidata.multidata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multidata.multidata.models.DataBase;



@Repository
public interface DatabaseRepository extends JpaRepository<DataBase,Long> {

}
