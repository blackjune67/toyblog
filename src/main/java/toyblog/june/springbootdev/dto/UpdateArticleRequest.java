package toyblog.june.springbootdev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public record UpdateArticleRequest(String title, String content) {
}
