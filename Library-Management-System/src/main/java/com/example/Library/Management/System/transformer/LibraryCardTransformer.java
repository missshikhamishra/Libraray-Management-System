package com.example.Library.Management.System.transformer;

import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.model.LibraryCard;

import java.util.UUID;

public class LibraryCardTransformer {

    public static LibraryCard prepareLibraryCard(){
        return LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .build();
    }
}
