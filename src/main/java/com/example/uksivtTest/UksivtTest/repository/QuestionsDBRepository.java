package com.example.uksivtTest.UksivtTest.repository;

import com.example.uksivtTest.UksivtTest.model.QuestionsDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsDBRepository  extends CrudRepository<QuestionsDB, Integer> {

    List<QuestionsDB> findByStudentId(int studentId);

}
