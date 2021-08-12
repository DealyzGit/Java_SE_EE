import com.psbc.www.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
//      获取Spring上下文对象！
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Hello hello = (Hello) applicationContext.getBean("Hello");
        hello.setName("tong");
        System.out.println(hello.show());
    }
}
