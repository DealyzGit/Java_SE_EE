import com.psbc.www.dao.UserDao;
import com.psbc.www.dao.UserDaoImpl;
import com.psbc.www.dao.UserDaoMysqlImpl;
import com.psbc.www.service.UserService;
import com.psbc.www.service.UserServiceImpl;

public class MyTest {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        UserDao userDao=new UserDaoImpl();
        userService.setUserDao(userDao);
        userService.getUser();
    }
}
