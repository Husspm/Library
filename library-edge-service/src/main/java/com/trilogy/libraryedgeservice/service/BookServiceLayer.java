package com.trilogy.libraryedgeservice.service;

import com.trilogy.libraryedgeservice.model.Book;
import com.trilogy.libraryedgeservice.model.Isbns;
import com.trilogy.libraryedgeservice.util.feign.BookFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookServiceLayer {
    private BookFeign bookFeign;

    @Autowired
    public BookServiceLayer(BookFeign bookFeign) {
        this.bookFeign = bookFeign;
    }

    public List<Book> getAllBooks(){
       return bookFeign.getAllBooks();
    }

   public String checkOutBooks(Isbns isbns){
       boolean allGood = false;
       String day = isbns.getDay();
       if((day.equals("Monday") || day.equals("Wednesday") || day.equals("Friday")) && isbns.getList().size() > 3){
            return "Sorry that's too many books for today";
       } else if((day.equals("Tuesday") || day.equals("Thursday")) && isbns.getList().size() > 2){
           return "Sorry that's too many books for today";
       } else if (day.equals("Saturday") && isbns.getList().size() > 4){
           return "Sorry that's too many books for today";
       } else if (day.equals("Sunday") && isbns.getList().size() > 1){
           return "Sorry that's too many books for today";
       } else {
           List<Book> allBooks = getAllBooks();
           List<String> allIsbns = isbns.getList();
           for (int i = 0; i < allIsbns.size(); i++) {
               for (int j = 0; j < allBooks.size(); j++) {
                   if (allBooks.get(j).getIsbn().equals(allIsbns.get(i))) {
                       allGood = true;
                   }
               }
               if (allGood == false) {
                   return "Sorry a book you requested is not in stock";
               }
           }
       }
       if (allGood == true) {
           return "You have successfully checked out all your books";
       }
       return null;
   }


}
