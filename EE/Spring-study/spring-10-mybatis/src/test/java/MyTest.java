import com.psbc.pojo.User;
import com.psbc.pojo.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void test() {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession=build.openSession(true);

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList=mapper.selectUser();

            for (User user:userList
                 ) {
                System.out.println(user);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
