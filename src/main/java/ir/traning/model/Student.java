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
public class Student extends BaseEntity {

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "NATIONAL_CODE", unique = true)
    private String nationalCode;
    @Temporal(TemporalType.DATE)
    private Date dob;

    @ManyToMany
    @JoinTable(
            name = "j_student_course"
            , joinColumns = {@JoinColumn(name = "fk_student")},
            inverseJoinColumns = {@JoinColumn(name = "fk_course")}
    )
    private List<Course> courses = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name ="j_student_exam",
            joinColumns  ={@JoinColumn(name = "fk_student")},
            inverseJoinColumns = {@JoinColumn(name="fk_exam")}
            )
    private List<Exam> exams = new ArrayList<>();

}
