package com.codingdojo.dielleza.beltexam.repositories;

import com.codingdojo.dielleza.beltexam.models.TableMaster;
import com.codingdojo.dielleza.beltexam.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableMasterRepository extends CrudRepository<TableMaster,Long> {

    List<TableMaster>findAll();

    List<TableMaster> findAllByUsers(User user);
    List<TableMaster> findByUsersNotContains(User user);
    List<TableMaster> findAllByOrderByCreatedAtDesc();

}
