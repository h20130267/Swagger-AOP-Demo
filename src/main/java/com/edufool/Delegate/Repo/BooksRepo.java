package com.edufool.Delegate.Repo;

import com.edufool.libraryservice.models.Book;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BooksRepo {
    public List<Book> getStaticBooksList() {

        return Arrays.asList(
                new Book().name("Digital Design").bookAuthor("Morris Mano"),
                new Book().name("Scion OF Ikshvaku").bookAuthor("Amish Tripathi")
        );
    }
}
