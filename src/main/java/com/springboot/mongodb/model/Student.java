package com.springboot.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "student")
public class Student {
    @Transient
    public static final String SEQUENCE_NAME="student_sequence";
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Gender gender;
    private Long totalSpentOnBooks;
    private List<String> favouriteSubjects;
    public void update(Student student)
    {
        this.setEmail(student.getEmail());
        this.setFirstName(student.getFirstName());
        this.setLastName(student.getLastName());
        this.setGender(student.getGender());
        this.setTotalSpentOnBooks(student.getTotalSpentOnBooks());
        this.setFavouriteSubjects(student.getFavouriteSubjects());
        this.getAddress().update(student.getAddress());
    }
}
