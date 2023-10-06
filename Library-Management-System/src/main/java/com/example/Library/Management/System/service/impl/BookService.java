package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.Enum.Genre;
import com.example.Library.Management.System.dto.responsetDTO.AuthorResponse;
import com.example.Library.Management.System.dto.responsetDTO.BookResponse;
import com.example.Library.Management.System.exception.AuthorNotFoundException;
import com.example.Library.Management.System.exception.BookNotAvailableException;
import com.example.Library.Management.System.model.Author;
import com.example.Library.Management.System.model.Book;
import com.example.Library.Management.System.repository.AuthorRepository;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.transformer.AuthorTransformer;
import com.example.Library.Management.System.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public String addBook(Book book) {

        // check if author exists or not
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid author id!!!");
        }

        Author author = authorOptional.get();
        book.setAuthor(author);
        author.getBooks().add(book);

        authorRepository.save(author);  // save both author and book
        return "Book added successfully";

    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThan(String genre, double cost) {

        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThan(genre,cost);

        // prepare the response. convert model to dto
        List<BookResponse> response = new ArrayList<>();
        for(Book book: books){
            response.add(BookTransformer.BookToBookResponse(book));
        }
        return response;
    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost) {

        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThanHQL(genre,cost);

        // prepare the response. convert model to dto
        List<BookResponse> response = new ArrayList<>();
        for(Book book: books){
            response.add(BookTransformer.BookToBookResponse(book));
        }
        return response;
    }
    public String deleteBook(int id) {
        Optional<Book> bookOptional= bookRepository.findById(id);
        if(bookOptional.isEmpty()){
            throw new BookNotAvailableException("Invalid Book Id!");
        }
        bookRepository.deleteById(id);
        return "Book Deleted Successfully!";
    }

    public List<AuthorResponse> GetAllAuthorsWithGenreX(Genre genre) {
        List<Author> authors=bookRepository.findAllAuthorsWithGenreX(genre);
        List<AuthorResponse> authorResponses= new ArrayList<>();
        for (Author author: authors){
            authorResponses.add(AuthorTransformer.AuthorToAuthorResponse(author));
        }
        if (authorResponses.isEmpty()){
            throw new AuthorNotFoundException("No author present with specific genre!");
        }
        return authorResponses;
    }

    public List<BookResponse> GetBooksHavingPagesBetweenAAndB(int a, int b) {
        List<Book> books=bookRepository.findBooksHavingPagesBetweenAAndB(a, b);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book: books){
            bookResponses.add(BookTransformer.BookToBookResponse(book));
        }
        if(bookResponses.isEmpty()){
            throw new BookNotAvailableException("No books available of required type!");
        }
        return bookResponses;
    }

    public List<BookResponse> getBookByGenre(Genre genre) {
        List<Book> books=bookRepository.findByGenre(genre);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book: books){
            bookResponses.add(BookTransformer.BookToBookResponse(book));
        }
        if(bookResponses.isEmpty()){
            throw new BookNotAvailableException("No books available of required genre!");
        }
        return bookResponses;
    }
}
