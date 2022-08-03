package com.testtask.ursip.service;

import com.testtask.ursip.entity.Document;
import com.testtask.ursip.exception.TaskException;
import com.testtask.ursip.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    public Document saveNewDocument(Document newDocument) {
        if(newDocument == null) {
            throw new TaskException("Document wasn't set!");
        }
        String documentNumber = newDocument.getDocumentNumber();
        if(documentWithGivenNumberPresentedInDB(documentNumber)){
            throw new TaskException("Document with given number " + documentNumber + " was found in database!");
        }

        Date creationDate = new Date();
        newDocument.setCreatedDate(creationDate);
        newDocument.setLasModifiedDate(creationDate);
        return documentRepository.save(newDocument);
    }

    public Document saveEditedDocument(Document editedDocument) {
        if(editedDocument == null) {
            throw new TaskException("Document wasn't set!");
        }
        int documentId = editedDocument.getId();
        Document documentFromDb = documentRepository.findById(documentId)
                .orElseThrow(() -> new TaskException("Document with id " + documentId + " wasn't found!"));


        Date editionDate = new Date();
        documentFromDb.setDocumentNumber(editedDocument.getDocumentNumber());
        documentFromDb.setLasModifiedDate(editionDate);
        return documentRepository.save(documentFromDb);
    }

    public void deleteDocumentById(int documentId) {
        if(documentRepository.findById(documentId).isEmpty()) {
            throw new TaskException("Document with id " + documentId + " wasn't found!");
        }
        documentRepository.deleteById(documentId);
    }



    public boolean documentWithGivenNumberPresentedInDB(String documentNumber) {
        return !Objects.isNull(documentRepository.findDocumentByDocumentNumber(documentNumber));
    }
}
