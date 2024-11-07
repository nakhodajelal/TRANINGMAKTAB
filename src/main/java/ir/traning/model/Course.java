package ir.traning.model;

import ir.traning.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "courses")
public class Course extends BaseEntity {
    @Column(name = "TITLE")
    private String title;
    @Column(name = "UNIT")
    private int unit;

    @ManyToMany(mappedBy = "courses")
    List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "course")
  private   List<Exam> exams = new ArrayList<>();


}
