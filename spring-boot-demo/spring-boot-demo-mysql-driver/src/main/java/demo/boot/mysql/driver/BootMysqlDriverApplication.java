package demo.boot.mysql.driver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 数据库连接
 *
 * @author leishiguang
 * @since v1.0
 */

@SpringBootApplication
@EnableWebMvc
public class BootMysqlDriverApplication {

  public static void main(String[] args) throws Exception {
    Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor()
        .newInstance();
    // Do something with the Driver


    Connection conn = DriverManager
        .getConnection("jdbc:mysql://localhost/test?" + "user=minty&password=greatsqldb");
    // Do something with the Connection
  }
}

