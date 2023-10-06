package com.example.Library.Management.System.dto.responsetDTO;

import com.example.Library.Management.System.Enum.CardStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LibraryCardReponse {

    String cardNo;

    CardStatus cardStatus;

    Date issueDate;
}
