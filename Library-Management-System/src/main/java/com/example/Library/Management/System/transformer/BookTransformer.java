package com.example.Library.Management.System.transformer;

import com.example.Library.Management.System.dto.responsetDTO.BookResponse;
import com.example.Library.Management.System.model.Book;

public class BookTransformer {

    public static BookResponse BookToBookResponse(Book book){

        return BookResponse.builder()
                .authorName(book.getAuthor().getName())
                .cost(book.getCost())
                .genre(book.getGenre())
                .noOfPages(book.getNoOfPages())
                .title(book.getTitle())
                .build();
    }
}
