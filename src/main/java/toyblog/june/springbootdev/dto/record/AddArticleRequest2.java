package toyblog.june.springbootdev.dto.record;

import lombok.Builder;
import toyblog.june.springbootdev.domain.Article;

public record AddArticleRequest2(String title, String content, String author) {
    @Builder
    public Article toEntity(String author) {
        return Article
                .builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
