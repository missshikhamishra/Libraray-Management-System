package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.dto.responsetDTO.IssueBookResponse;
import com.example.Library.Management.System.Enum.TransactionStatus;
import com.example.Library.Management.System.dto.responsetDTO.IssueBookResponse;
import com.example.Library.Management.System.exception.BookNotAvailableException;
import com.example.Library.Management.System.exception.StudentNotFoundException;
import com.example.Library.Management.System.model.Book;
import com.example.Library.Management.System.model.Student;
import com.example.Library.Management.System.model.Transaction;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.repository.StudentRepository;
import com.example.Library.Management.System.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TransactionRepo transactionRepo;

    public IssueBookResponse issueBook(int bookId, int studentId) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Invalid student id!!");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new BookNotAvailableException("Invalid book id");
        }

        Book book = optionalBook.get();
        if(book.isIssued()){
            throw new BookNotAvailableException("Book already issued");
        }

        // issue the book
        Student student = studentOptional.get();

        // create transaction
        Transaction transaction = Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        Transaction savedTransaction = transactionRepo.save(transaction);

        // update book
        book.setIssued(true);
        book.getTransactions().add(savedTransaction);

        // card changes
        student.getLibraryCard().getTransactions().add(savedTransaction);

        Book savedBook = bookRepository.save(book);  // book and transaction
        Student savedStudent = studentRepository.save(student);  // student and transaction

       //  send an email
        String text = "Hi! " + student.getName() + " The below book has been issued to you\n" +
                book.getTitle() + " \nThis is the transaction number: "+savedTransaction.getTransactionNumber();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("acciojobspringbackend@gmail.com");
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject("Congrats!! Book Issued");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        // prepare response
        return IssueBookResponse.builder()
                .bookName(savedBook.getTitle())
                .transactionStatus(savedTransaction.getTransactionStatus())
                .transactionTime(savedTransaction.getTransactionTime())
                .transactionNumber(savedTransaction.getTransactionNumber())
                .studentName(savedStudent.getName())
                .libraryCardNumber(savedStudent.getLibraryCard().getCardNo())
                .authorName(savedBook.getAuthor().getName())
                .build();

    }
}
