package com.testtask.ursip.repository;

import com.testtask.ursip.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Document findDocumentByDocumentNumber(String documentNumber);
}
