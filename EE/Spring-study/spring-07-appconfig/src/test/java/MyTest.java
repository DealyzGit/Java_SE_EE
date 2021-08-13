import com.psbc.www.config.Config;
import com.psbc.www.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
//        完全使用配置类，只能通过AnnotationConfig上下文获取容器
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        User user = (User) context.getBean("getUser");

        System.out.println(user.getName());
    }
}
