package com.example.Library.Management.System.dto.responsetDTO;

import com.example.Library.Management.System.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {

    String title;

    int noOfPages;

    Genre genre;

    double cost;

    String authorName;
}
