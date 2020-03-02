package com.example.uksivtTest.UksivtTest.repository;

import com.example.uksivtTest.UksivtTest.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByLogin(String login);

    @Query(value = "SELECT * FROM student WHERE login LIKE %?1%", nativeQuery = true)
    List<Student> findByLoginLike(String login);

}
