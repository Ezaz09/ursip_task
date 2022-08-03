package com.testtask.ursip.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.testtask.ursip.entity.view.DocumentView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @JsonView({DocumentView.class,
               DocumentView.EditDocumentView.class})
    private int id;

    @Column(nullable = false, name = "document_number")
    @JsonView({DocumentView.class,
               DocumentView.CreateDocumentView.class,
               DocumentView.EditDocumentView.class})
    private String documentNumber;

    @Column(nullable = false, name = "created_date")
    @JsonView({DocumentView.class})
    private Date createdDate;

    @Column(name="last_modified_date")
    @JsonView({DocumentView.class})
    private Date lasModifiedDate;
}
