package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.dto.responsetDTO.AuthorResponse;
import com.example.Library.Management.System.dto.responsetDTO.BookResponse;
import com.example.Library.Management.System.exception.AuthorNotFoundException;
import com.example.Library.Management.System.exception.BookNotAvailableException;
import com.example.Library.Management.System.model.Author;
import com.example.Library.Management.System.model.Book;
import com.example.Library.Management.System.repository.AuthorRepository;
import com.example.Library.Management.System.service.AuthorService;
import com.example.Library.Management.System.transformer.AuthorTransformer;
import com.example.Library.Management.System.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);
        return "Author succesfully added!!!";
    }


        public AuthorResponse updateEmail(int id, String newEmail) {
            Optional<Author> authorOptional = authorRepository.findById(id);
            if(authorOptional.isEmpty()){
                throw new AuthorNotFoundException("Invalid author Id!");
            }
            Author author= authorOptional.get();
            author.setEmailId(newEmail);

            Author savedAuthor= authorRepository.save(author);

            return AuthorTransformer.AuthorToAuthorResponse(savedAuthor);
        }

    public List<BookResponse> getAllBookWrittenByX(int id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid author Id!");
        }
        Author author= authorOptional.get();
        if(author.getBooks().size()==0){
            throw new BookNotAvailableException("Author Does Not Registered Any Book Yet!");
        }
        List<BookResponse> response= new ArrayList<>();
        for(Book book:author.getBooks()){
            response.add(BookTransformer.BookToBookResponse(book));
        }
        return response;
    }

    public List<AuthorResponse> getAuthorWithMoreThanXBooks(int x) {
        List<Author> authors= new ArrayList<>();
        authors= authorRepository.getAuthorWithMoreThanXBooks(x);
        List<AuthorResponse> response= new ArrayList<>();
        for (Author author: authors){
            response.add(AuthorTransformer.AuthorToAuthorResponse(author));
        }
        return response;

    }
}
