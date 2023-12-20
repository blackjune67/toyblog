package toyblog.june.springbootdev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyblog.june.springbootdev.domain.Article;

public record AddArticleRequest2(String title, String content) {
    @Builder
    public Article toEntity() {
        return Article
                .builder()
                .title(title)
                .content(content)
                .build();
    }
}
