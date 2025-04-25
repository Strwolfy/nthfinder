package com.github.nthfinder.nthfinder.controller;

import com.github.nthfinder.nthfinder.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
// import io.springfox.documentation.annotations.ApiIgnore; // удалите такие импорты

@RestController
@RequestMapping("/api")
@Tag(name = "XLSX File Processing", description = "API for finding N-th minimal number in XLSX files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/find-nth-min")
    @Operation(summary = "Find N-th minimal number in XLSX file",
            description = "Returns N-th smallest number from the first column of XLSX")
    public ResponseEntity<Integer> findNthMin(
            @RequestParam
            @Parameter(description = "Absolute path to XLSX file", example = "/home/user/numbers.xlsx", required = true)
            String filePath,

            @RequestParam
            @Parameter(description = "Position N (1-based index)", example = "5", required = true)
            int n) {
        try {
            Integer result = fileService.findNthMinNumber(filePath, n);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}