package com.web.mb;

import com.web.emp.EmpDAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;
    @GetMapping("/search")
    public String searchForm(){
        return "th/mb/bookSearch";
    }

    @PostMapping("/search")
    @ResponseBody
    public List<Book> searchBook(@RequestParam Map<String,String> info){
        log.info("search form : {}", info);
        List<Book> books = bookMapper.searchBook(info);
        return books;
    }

    @GetMapping("/search/{low}/{high}")
    @ResponseBody
    public List<Book> searchPrice(@PathVariable int low, @PathVariable int high){
        log.info("search range : {}/{}", low,high);
        List<Book> books = bookMapper.searchPrice(low,high);
        return books;
    }

    @GetMapping({"/search/author","/search/author/{author}"})
    @ResponseBody
    public List<Book> searchAuthor(@PathVariable (required=false) String author){
        log.info("search range : {}", author);
        List<Book> books = bookMapper.searchAuthor(author);
        return books;
    }



    @GetMapping({"/search/pubAndPrice"})
    @ResponseBody
    public List<Book> searchByPubAndPrice(Book book){
        log.info("search range : {}/{}", book.getPublisher(),book.getPrice());

        List<Book> books = bookMapper.PubAndPrice(book);
        return books;
    }

}
