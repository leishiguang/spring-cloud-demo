package demo.boot.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * web 首页
 *
 * @author leishiguang
 * @since v1.0
 */
@RestController
public class IndexController {

  @RequestMapping("/index")
  public String index(){
    return "success";
  }
}
