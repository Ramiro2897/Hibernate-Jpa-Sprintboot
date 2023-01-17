package com.javatechnolessons.demo.repository;

import java.util.List;

import com.javatechnolessons.demo.model.Employee;
import com.javatechnolessons.demo.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IEmployeeJpaRepository extends JpaRepository<Employee, Long> {
   
// seleccionamos campos de empleado donde employeeid='[param]'
    Employee findByEmployeeid(String employeeid);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByRole(Role role);

}
