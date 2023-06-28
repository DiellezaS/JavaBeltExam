package com.codingdojo.dielleza.beltexam.services;

import com.codingdojo.dielleza.beltexam.models.TableMaster;
import com.codingdojo.dielleza.beltexam.models.User;
import com.codingdojo.dielleza.beltexam.repositories.TableMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableMasterService {
    @Autowired
    private TableMasterRepository tableMasterRepository;

    public List<TableMaster> findAll(){
        return tableMasterRepository.findAll();
    }

    public List<TableMaster> getAssigned(User user){
        return tableMasterRepository.findAllByUsers(user);
    }

    public List<TableMaster> getUnassigned(User user){
        return tableMasterRepository.findByUsersNotContains(user);
    }
    public  List<TableMaster> getByTime(){
        return  tableMasterRepository.findAllByOrderByCreatedAtDesc();
    }

    public  TableMaster add(TableMaster tableMaster){
        return tableMasterRepository.save(tableMaster);

    }

    public  TableMaster findById(Long id){
        return tableMasterRepository.findById(id).orElse(null);
    }

    public void delete(TableMaster tableMaster){
        tableMasterRepository.delete(tableMaster);

    }

}
