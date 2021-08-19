package com.kuang.controller;

import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();

        model.addAttribute("list", list);
        return "allBook";
    }

    @RequestMapping("/toAddBook")
    public String toAddPage() {
        return "addBook";

    }

    @RequestMapping("/addBook")
    public String addBook(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/toUpdate")
    public String toUpdateBook(int id, Model model) {
        Books books = bookService.queryById(id);
        model.addAttribute("books", books);
        return "toUpdateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Books books) {
        System.out.println(books);
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/queryBook")
    public String deleteBook(String bookName, Model model) {
        List<Books> list =new ArrayList<>();
        list = bookService.queryBookByName(bookName);
        if (list.size()<1) {
            model.addAttribute("error","未查到！");
            list = bookService.queryAllBook();
        }
        model.addAttribute("list", list);
        return "allBook";
    }


}
