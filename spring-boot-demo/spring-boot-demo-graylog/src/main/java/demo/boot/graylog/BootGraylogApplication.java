package demo.boot.graylog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * graylog应用启动类
 *
 * @author leishiguang
 * @since v1.0
 */
@SpringBootApplication
@EnableWebMvc
public class BootGraylogApplication {
  public static void main(String[] args) {
    SpringApplication.run(BootGraylogApplication.class);
  }
}
