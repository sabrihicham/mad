package dz.univbechar.mad;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
        @RequestMapping("/")
        public String hello()   {
           return "Hello User";
        }
}
