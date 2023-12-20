package toyblog.june.springbootdev.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import toyblog.june.springbootdev.domain.Article;

//@Getter
public record ArticleResponse(String title, String content) {

    public ArticleResponse(Article article) {
        this(article.getTitle(), article.getContent());
    }
}