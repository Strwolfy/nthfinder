package com.github.nthfinder.nthfinder.service;


import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

@Service
public class FileService {

    // Явное создание логгера
    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    public Integer findNthMinNumber(String filePath, int n) throws IOException {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    int number = (int) cell.getNumericCellValue();
                    if (maxHeap.size() < n) {
                        maxHeap.offer(number);
                    } else if (number < maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.offer(number);
                    }
                }
            }

            if (maxHeap.size() < n) {
                throw new IllegalArgumentException("Not enough numbers in file for N = " + n);
            }

            return maxHeap.peek();
        } catch (Exception e) {
            log.error("File processing error: {}", e.getMessage());
            throw new IOException("Error reading file: " + e.getMessage());
        }
    }
}