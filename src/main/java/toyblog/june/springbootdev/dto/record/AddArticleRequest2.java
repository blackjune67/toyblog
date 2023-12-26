package toyblog.june.springbootdev.dto.record;

import lombok.Builder;
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
