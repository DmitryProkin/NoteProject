package com.dmitry.noteProject.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Note", schema = "public", catalog = "notebase")
public class NoteEntity {
    private int id;
    private String name;
    private String message;
    private Timestamp dateCreate;
    private Timestamp dateUpdate;
//    private int dateId;
//    private DateEntity dateByDateId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "dateCreate")
    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }
    @Basic
    @Column(name = "dateUpdate")
    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        NoteEntity that = (NoteEntity) o;
//        return id == that.id &&
//                date == that.date &&
//                Objects.equals(name, that.name) &&
//                Objects.equals(message, that.message) ;
//    }

    @PrePersist
    protected void onCreate() {
        dateCreate = new Timestamp(System.currentTimeMillis());
        dateUpdate = dateCreate;
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdate = new Timestamp(System.currentTimeMillis());
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, message, dateId);
//    }

//    @ManyToOne
//    @JoinColumn(name = "date_id", referencedColumnName = "id", nullable = false)
//    public DateEntity getDateByDateId() {
//        return dateByDateId;
//    }

//    public void setDateByDateId(DateEntity dateByDateId) {
//        this.dateByDateId = dateByDateId;
//    }
}
