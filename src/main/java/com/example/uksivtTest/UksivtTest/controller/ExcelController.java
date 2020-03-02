package com.example.uksivtTest.UksivtTest.controller;

import com.example.uksivtTest.UksivtTest.model.QuestionsDB;
import com.example.uksivtTest.UksivtTest.model.QuestionsJson;
import com.example.uksivtTest.UksivtTest.model.Student;
import com.example.uksivtTest.UksivtTest.repository.QuestionsDBRepository;
import com.example.uksivtTest.UksivtTest.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private QuestionsDBRepository questionsDBRepository;

    @GetMapping(value = "/get/excel/doc")
    public void getFile(HttpServletResponse response) {
        try {
            File file = new File("stat.xls");
            file.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file); Workbook book = new HSSFWorkbook()){
                List<Student> students = new ArrayList<>();
                studentService.findAll().forEach(students::add);

                Sheet sheet = book.createSheet("stat");
                Row row = sheet.createRow(0);

                Field[] fields = QuestionsJson.class.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(fields[i].getName()
                            .replaceAll("q", "в")
                            .replaceAll("text", "др")
                            .replaceAll("_", "."));
                }

                ObjectMapper objectMapper = new ObjectMapper();

                int rowInt = 1;
                for (Student student : students) {
                    row = sheet.createRow(rowInt);
                    QuestionsDB questionsDB = questionsDBRepository.findByStudentId(student.getId());

                    System.out.println(student.getId());
                    System.out.println(questionsDB);
                    QuestionsJson questionsJson = objectMapper.readValue(questionsDB.getJson(), QuestionsJson.class);
                    for (int i = 0; i < fields.length; i++) {
                        Cell cell = row.createCell(i);
                        fields[i].setAccessible(true);
                        cell.setCellValue((String) fields[i].get(questionsJson));
                        fields[i].setAccessible(false);
                    }
                }

                sheet.autoSizeColumn(1);
                book.write(fileOutputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.setContentType("application/xls");
            response.setHeader("Content-Disposition", "attachment; filename=\"stat.xls\"");
            InputStream is = new FileInputStream(file);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

}
