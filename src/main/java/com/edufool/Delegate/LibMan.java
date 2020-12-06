package com.edufool.Delegate;

import com.edufool.Delegate.Repo.BooksRepo;
import com.edufool.libraryservice.api.LibraryApi;
import com.edufool.libraryservice.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibMan implements LibraryApi {

    @Autowired
    private BooksRepo booksRepo;

    @Override
    public ResponseEntity<List<Book>> getAllBooksInLibrary() {
        return new ResponseEntity(booksRepo.getStaticBooksList(), HttpStatus.OK);
    }


}
