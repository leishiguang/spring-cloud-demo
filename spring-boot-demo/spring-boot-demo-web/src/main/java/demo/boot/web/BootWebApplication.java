package demo.boot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 启动类
 *
 * @author leishiguang
 * @since v1.0
 */
@EnableWebMvc
@SpringBootApplication
public class BootWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootWebApplication.class);
  }
}
