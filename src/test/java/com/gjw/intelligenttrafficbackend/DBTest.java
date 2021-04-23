package com.gjw.intelligenttrafficbackend;

import com.gjw.intelligenttrafficbackend.dao.UserDao;
import com.gjw.intelligenttrafficbackend.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DBTest {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private UserDao userDao;
    @Test
    public void testDBConnection() throws SQLException {

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void testUserDao(){

        List<User> users = userDao.selectList(null);
        users.forEach(user->{
            System.out.println(user);
        });
    }
}
