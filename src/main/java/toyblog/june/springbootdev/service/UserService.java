package toyblog.june.springbootdev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import toyblog.june.springbootdev.domain.User;
import toyblog.june.springbootdev.dto.AddUserRequest;
import toyblog.june.springbootdev.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest addUserRequest) {
        return userRepository.save(User.builder()
                        .email(addUserRequest.getEmail())
                        .password(bCryptPasswordEncoder.encode(addUserRequest.getPassword()))
                        .build())
                .getId();
    }
}