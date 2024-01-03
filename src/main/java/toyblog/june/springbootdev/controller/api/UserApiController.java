package toyblog.june.springbootdev.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import toyblog.june.springbootdev.dto.AddUserRequest;
import toyblog.june.springbootdev.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest addUserRequest) {
        userService.save(addUserRequest);
        return "redirect:/login";
    }
}
