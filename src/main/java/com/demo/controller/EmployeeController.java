package com.demo.controller;


import com.demo.entity.EmployeeEntity;
import com.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmployees")
    public ResponseEntity<?>  getAllEmployees(){
    List<EmployeeEntity> getAll= employeeService.getAll();
        return new ResponseEntity<>(getAll, HttpStatus.OK);
    }
    @GetMapping("/getByDept/{dept}")
    public List<EmployeeEntity> getDeptHr(@PathVariable String dept){
        return employeeService.getDept(dept);
    }
    @GetMapping("/countDept")
    public Map<String,Integer> getCount(){
        return employeeService.countDept();
    }
    @PostMapping("/addEmp")
    public ResponseEntity<?> addEmployees(@RequestBody EmployeeEntity entity){
    employeeService.addEmployees(entity);
      return  new ResponseEntity<>("{status:success}",HttpStatus.OK);
    }
    @PutMapping("/updateEmp")
    public ResponseEntity<?> updateEmp(@RequestBody EmployeeEntity entity,@RequestParam("id") int id){
        employeeService.updateById(entity,id);
        return new ResponseEntity<>("{status:success}",HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmp(@RequestParam int id){
        employeeService.deleteById(id);
        return new ResponseEntity<>("{status:success}",HttpStatus.OK);
    }

}
