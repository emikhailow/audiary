package com.ewm.diary.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "records")
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "gd_id")
    private String googleDriveId;

    public Record(String fileName) {
        this.fileName = fileName;
    }
}
