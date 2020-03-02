package com.example.uksivtTest.UksivtTest.repository;

import com.example.uksivtTest.UksivtTest.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByLogin(String login);

}
