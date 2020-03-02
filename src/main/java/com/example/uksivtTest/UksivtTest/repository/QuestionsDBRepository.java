package com.example.uksivtTest.UksivtTest.repository;

import com.example.uksivtTest.UksivtTest.model.QuestionsDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsDBRepository  extends CrudRepository<QuestionsDB, Integer> {

    QuestionsDB findByStudentId(int studentId);

}
