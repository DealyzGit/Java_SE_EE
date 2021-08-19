import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import com.kuang.service.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        bookServiceImpl.queryAllBook();
        for (Books book: bookServiceImpl.queryAllBook()
             ) {
            System.out.println(book);

        }

    }
}
