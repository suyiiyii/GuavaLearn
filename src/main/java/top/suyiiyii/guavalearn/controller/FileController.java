package top.suyiiyii.guavalearn.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.suyiiyii.guavalearn.dto.CommonResponse;
import top.suyiiyii.guavalearn.models.Grade;
import top.suyiiyii.guavalearn.service.GradeService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@RestController
public class FileController {
    @Autowired
    private GradeService gradeService;

    @PostMapping("/upload")
    public CommonResponse upload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("upload");
        File tempFile = Files.createTempFile(null, null).toFile();
        file.transferTo(tempFile);
        System.out.println("tempFile = " + tempFile);
        FileReader fileReader = new FileReader(tempFile);
        System.out.println("fileReader = " + fileReader);

        gradeService.addByCsvFile(fileReader);
        return new CommonResponse("上传成功");

    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        List<Grade> grades = gradeService.getAllGrades();

        StringWriter writer = new StringWriter();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("studentid", "grade"));
        for (Grade grade : grades) {
            csvPrinter.printRecord(grade.getStudentid(), grade.getGrade());
        }
        csvPrinter.flush();
        csvPrinter.close();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"grades.csv\"");
        InputStream inputStream = new ByteArrayInputStream(writer.toString().getBytes(StandardCharsets.UTF_8));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }


}
