package com.example.Library.Management.System.repository;

import com.example.Library.Management.System.Enum.Genre;
import com.example.Library.Management.System.model.Author;
import com.example.Library.Management.System.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "select * from book where genre = :genre and cost > :cost",nativeQuery = true)
    List<Book> getBooksByGenreAndCostGreaterThan(String genre, double cost);

    @Query(value = "select b from Book b where b.genre = :genre and b.cost > :cost")
    List<Book> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost);

    @Query(value = "select distinct b.author from Book b where b.genre=:genre")
    List<Author> findAllAuthorsWithGenreX(Genre genre);

    @Query(value = "select b from Book b where b.noOfPages>:a and b.noOfPages<:b")
    List<Book> findBooksHavingPagesBetweenAAndB(int a, int b);

    List<Book> findByGenre(Genre genre);
}
