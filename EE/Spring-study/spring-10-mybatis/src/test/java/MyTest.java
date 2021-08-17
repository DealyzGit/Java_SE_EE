import com.psbc.pojo.User;
import com.psbc.pojo.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    //    @Test
    //    public void test() {
    //        String resource = "mybatis-config.xml";
    //        try {
    //            InputStream inputStream = Resources.getResourceAsStream(resource);
    //            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
    //            SqlSession sqlSession = build.openSession(true);
    //
    //            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    //            List<User> userList = mapper.selectUser();
    //
    //            for (User user : userList
    //            ) {
    //                System.out.println(user);
    //
    //            }
    //
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //
    //    }

    @Test
    public void Test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper2", UserMapper.class);
        for (User user : userMapper.selectUser()
        ) {
            System.out.println(user);
        }

    }
}
