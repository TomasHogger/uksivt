package com.example.uksivtTest.UksivtTest.controller;

import com.example.uksivtTest.UksivtTest.model.Questions;
import com.example.uksivtTest.UksivtTest.model.QuestionsDB;
import com.example.uksivtTest.UksivtTest.model.QuestionsJson;
import com.example.uksivtTest.UksivtTest.model.Student;
import com.example.uksivtTest.UksivtTest.repository.QuestionsDBRepository;
import com.example.uksivtTest.UksivtTest.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private QuestionsDBRepository questionsDBRepository;

    @PostMapping("save_case")
    public String saveCase(@RequestBody Questions questions) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findByLogin(user.getUsername());

        QuestionsJson questionsJson = new QuestionsJson();

        Field[] fields = questions.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().getName().equals("java.util.List")) {
                List<String> values = (List<String>) field.get(questions);
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        if (field.getName().equals("q9") || field.getName().equals("q10")) {
                            Field field1 = questionsJson.getClass().getDeclaredField(field.getName() + "_" + (i + 1));
                            field1.setAccessible(true);
                            field1.set(questionsJson, values.get(i));
                            field1.setAccessible(false);
                        } else {
                            Field field1 = questionsJson.getClass().getDeclaredField(field.getName() + "_" + values.get(i));
                            field1.setAccessible(true);
                            field1.set(questionsJson, "1");
                            field1.setAccessible(false);
                        }
                    }
                }
            } else {
                Field field1 = questionsJson.getClass().getDeclaredField(field.getName());
                field1.setAccessible(true);
                field1.set(questionsJson, field.get(questions));
                field1.setAccessible(true);
            }
            field.setAccessible(false);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        QuestionsDB questionsDB = new QuestionsDB();
        questionsDB.setStudentId(student.getId());
        questionsDB.setJson(objectMapper.writeValueAsString(questionsJson));
        questionsDBRepository.save(questionsDB);

        student.setAlreadyCompleted(true);
        studentService.save(student);

        return "./login";
    }

}
