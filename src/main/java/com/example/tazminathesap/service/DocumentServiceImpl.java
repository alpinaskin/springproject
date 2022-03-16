package com.example.tazminathesap.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.example.tazminathesap.model.MaddiTazminat;
import com.example.tazminathesap.util.DocxFileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocxFileUtil dUtil;

    @Override
    public void creatingDocxFile(MaddiTazminat maddiTazminat) {
        try {
            dUtil.returnDocFile(maddiTazminat);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<byte[]> fetchDocxFile(String fileName) {

        try {
            File file = ResourceUtils.getFile("classpath:docs/" + fileName);
            byte[] contents = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment().filename("yourfile.docx").build());
            return new ResponseEntity<>(contents, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
