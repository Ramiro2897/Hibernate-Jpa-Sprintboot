package com.javatechnolessons.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.javatechnolessons.demo.model.Employee;
import com.javatechnolessons.demo.model.Project;
import com.javatechnolessons.demo.model.Role;
// import com.javatechnolessons.demo.repository.IEmployeeJpaRepository;
// import com.javatechnolessons.demo.repository.IProjectJpaRepository;
// import com.javatechnolessons.demo.repository.IRoleJpaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
// @AutoConfigureTestDatabase (connection = EmbeddedDatabaseConnection.H2)
public class EmployeeJpaRepositoyTest {
    @Autowired
    private IEmployeeJpaRepository repoEmpl;

    @Autowired
    private IRoleJpaRepository repoRole;

    @Autowired
    private IProjectJpaRepository repoProj;

    @Test
    public void saveEmployee() {

        Role admin = new Role("ROLE_ADMIN");
        Role dev = new Role("ROLE_DEV");

        admin = repoRole.save(admin);
        dev = repoRole.save(dev);

        Project proj1 = new Project("proj1");
        Project proj2 = new Project("proj2");
        Project proj3 = new Project("proj3");

        proj1 = repoProj.save(proj1);
        proj2 = repoProj.save(proj2);
        proj3 = repoProj.save(proj3);

        Employee ramiro = new Employee("ramiro", "Gonzalez", "empl123", dev);
        Employee clara = new Employee("Clara", "Vargas", "empl124", admin);

        ramiro.getProjects().add(proj1);
        ramiro.getProjects().add(proj2);

        clara.getProjects().add(proj1);
        clara.getProjects().add(proj2);
        clara.getProjects().add(proj3);

        repoEmpl.save(ramiro);
        repoEmpl.save(clara);

        repoEmpl.flush();

        Employee empl124 = repoEmpl.findByEmployeeid("empl124");
        assertEquals("Clara", empl124.getFirstName());
        assertEquals(2, repoEmpl.findAll().size());
        assertEquals(admin, empl124.getRole());

    }
}
