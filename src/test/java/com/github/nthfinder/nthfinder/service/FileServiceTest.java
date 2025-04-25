package com.github.nthfinder.nthfinder.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    private String testFilePath;

    @BeforeEach
    void setup() throws IOException {
        // Создаём временный Excel файл
        File tempFile = File.createTempFile("test_numbers", ".xlsx");
        testFilePath = tempFile.getAbsolutePath();
        generateTestExcelFile(testFilePath);
    }

    @Test
    void testFindNthMinNumber_Success() throws IOException {
        // Given
        int n = 7;

        // When
        Integer result = fileService.findNthMinNumber(testFilePath, n);

        // Then
        assertNotNull(result);
        assertTrue(result <= 10);
    }


    @Test
    void testFindNthMinNumber_FileNotFound() {
        // Given
        String wrongPath = "C:\\nonexistent\\file.xlsx";

        // When & Then
        assertThrows(IOException.class, () -> fileService.findNthMinNumber(wrongPath, 3));
    }

    @Test
    void testFindNthMinNumber_EmptyFile() throws IOException {
        // Given
        String emptyFilePath = File.createTempFile("empty", ".xlsx").getAbsolutePath();
        generateEmptyExcelFile(emptyFilePath);

        // When & Then
        assertThrows(IOException.class, () -> fileService.findNthMinNumber(emptyFilePath, 1));
    }

    // Вспомогательный метод для генерации Excel файла
    private void generateTestExcelFile(String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.createSheet("Numbers");
            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(random.nextInt(10) + 1);
            }

            workbook.write(fileOut);
        }
    }

    private void generateEmptyExcelFile(String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(filePath)) {

            workbook.createSheet("Empty");
            workbook.write(fileOut);
        }
    }
}