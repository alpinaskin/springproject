package com.example.tazminathesap.service;

import com.example.tazminathesap.model.MaddiTazminat;

import org.springframework.http.ResponseEntity;

public interface DocumentService {
    public void creatingDocxFile(MaddiTazminat maddiTazminat);

    public ResponseEntity<byte[]> fetchDocxFile(String fileName);
}
