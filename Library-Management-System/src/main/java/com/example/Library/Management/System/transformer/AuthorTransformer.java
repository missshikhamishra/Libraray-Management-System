package com.example.Library.Management.System.transformer;

import com.example.Library.Management.System.dto.requestDTO.AuthorRequest;
import com.example.Library.Management.System.dto.responsetDTO.AuthorResponse;
import com.example.Library.Management.System.model.Author;

public class AuthorTransformer {
   public  static Author AuthorRequestToAuthor(AuthorRequest authorRequest){
        return Author.builder()
                .name(authorRequest.getName())
                .age(authorRequest.getAge())
               .emailId(authorRequest.getEmail())
                .build();
    }
    public static AuthorResponse AuthorToAuthorResponse(Author author) {
        return AuthorResponse.builder()
                .name(author.getName())
                .email(author.getEmailId())
                .build();

    }
}
