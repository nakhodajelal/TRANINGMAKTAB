package ir.traning.model;

import ir.traning.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Exam extends BaseEntity {
    @Column(name = "EXAM_TITLE")
    private String examTitle;
    @Column(name = "SCORE")
    private Float score;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXAM_TIME")
    private Date examTime;
    @ManyToOne
    @JoinColumn(name = "fk_course")
    private Course course;

    @ManyToMany(mappedBy = "exams")
    private List<Student> students=new ArrayList<>();
}
