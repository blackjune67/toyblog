package toyblog.june.springbootdev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExam(Model model) {
        Person person = new Person(1L, "홍길동", 11, List.of("독서","헬스"));
        model.addAttribute("person", person);
        model.addAttribute("today", LocalDate.now());
        return "example";
    }

    private record Person(Long id, String name, int age, List<String> hobbies) {

    }
}
