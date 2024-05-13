package com.demo.service;


import com.demo.dao.EmployeeDao;
import com.demo.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<EmployeeEntity> getAll() {
        return employeeDao.findAll();
    }

    public List<EmployeeEntity> getDept(String dept) {
        return employeeDao.findAll().stream().
                filter(entity -> entity.getDept().equalsIgnoreCase(dept)).collect(Collectors.toList());
    }

    public Map<String, Integer> countDept() {
        Map<String, Integer> countMap = new HashMap<>();
        for (EmployeeEntity entity : employeeDao.findAll()) {
            countMap.put(entity.getDept(), countMap.getOrDefault(entity.getDept(), 0) + 1);
        }
        return countMap;
    }

    public void addEmployees(EmployeeEntity entity) {
        employeeDao.save(entity);
    }

    public void updateById(EmployeeEntity entity, int id) {
        Optional<EmployeeEntity> optionalEntity = employeeDao.findById(id);
        if (optionalEntity.isPresent()) {
            EmployeeEntity existingEntity = optionalEntity.get();
            existingEntity.setName(entity.getName());
            existingEntity.setDept(entity.getDept());
            existingEntity.setRole(entity.getRole());
            existingEntity.setYear(entity.getYear());
            // Save the updated entity back to the database
            employeeDao.save(existingEntity);
        }
    }
    public void deleteById(int id){
        employeeDao.deleteById(id);
    }
}

