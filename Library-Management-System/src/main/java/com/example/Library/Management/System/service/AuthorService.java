package com.example.Library.Management.System.service;

import com.example.Library.Management.System.dto.responsetDTO.AuthorResponse;
import com.example.Library.Management.System.dto.responsetDTO.BookResponse;
import com.example.Library.Management.System.model.Author;

import java.util.List;

public interface AuthorService {

    String addAuthor(Author author);
    AuthorResponse updateEmail(int id, String newEmail);
    List<BookResponse> getAllBookWrittenByX(int id);
    List<AuthorResponse> getAuthorWithMoreThanXBooks(int x);
}
