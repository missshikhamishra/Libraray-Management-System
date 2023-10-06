package com.example.Library.Management.System.model;

import com.example.Library.Management.System.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    int noOfPages;

    @Enumerated(EnumType.STRING)
    Genre genre;

    double cost;

    boolean issued;

    @ManyToOne
    @JoinColumn
    Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    List<Transaction> transactions = new ArrayList<>();

}
