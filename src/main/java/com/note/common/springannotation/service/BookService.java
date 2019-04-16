package com.note.common.springannotation.service;

import com.note.common.springannotation.dao.BookDao;
import com.sun.jersey.spi.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //@Qualifier("bookDao")
    //@Autowired(required=false)
    //@Resource(name="bookDao2")
    @Inject
    private BookDao bookDao;

    public void print() {
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService [bookDao=" + bookDao + "]";
    }

}
