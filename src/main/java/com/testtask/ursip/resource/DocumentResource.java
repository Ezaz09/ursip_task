package com.testtask.ursip.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.testtask.ursip.entity.Document;
import com.testtask.ursip.entity.view.DocumentView;
import com.testtask.ursip.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/document", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DocumentResource {
    private final DocumentService documentService;

    @GetMapping
    @ResponseBody
    @JsonView(DocumentView.class)
    public List<Document> getListOfDocuments() {
        return documentService.getDocuments();
    }

    @PostMapping
    @ResponseBody
    @JsonView(DocumentView.class)
    public ResponseEntity<Document> saveDocument(@RequestBody
                                                 @JsonView({DocumentView.CreateDocumentView.class})
                                                 Document newDocument) {
        Document savedNewDocument = documentService.saveNewDocument(newDocument);
        return new ResponseEntity<>(savedNewDocument, HttpStatus.OK);
    }

    @PutMapping
    @ResponseBody
    @JsonView(DocumentView.class)
    public ResponseEntity<Document> editDocument(@RequestBody
                                                 @JsonView({DocumentView.EditDocumentView.class})
                                                 Document document) {
        Document editedDocument = documentService.saveEditedDocument(document);
        return new ResponseEntity<>(editedDocument, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity deleteDocument(@PathVariable int id) {
        documentService.deleteDocumentById(id);
        return ResponseEntity.ok().build();
    }
    
}
