package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.model.Author;
import com.example.Library.Management.System.repository.AuthorRepository;
import com.example.Library.Management.System.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);
        return "Author succesfully added!!!";
    }
}
