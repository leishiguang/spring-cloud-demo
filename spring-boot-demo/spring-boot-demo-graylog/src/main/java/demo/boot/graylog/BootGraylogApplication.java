package demo.boot.graylog;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static Logger log = LoggerFactory.getLogger(BootGraylogApplication.class);

  public static void main(String[] args) {
    log.info("系统启动");
    SpringApplication.run(BootGraylogApplication.class);
    log.trace("trace日志");
    log.debug("debug日志");
    log.info("info日志");
    log.warn("warn日志");
    log.error("error日志");
  }
}
