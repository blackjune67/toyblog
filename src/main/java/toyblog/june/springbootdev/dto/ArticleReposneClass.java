package toyblog.june.springbootdev.dto;

import lombok.Getter;
import toyblog.june.springbootdev.domain.Article;

@Getter
public class ArticleReposneClass {

    private final String title;
    private final String content;

    public ArticleReposneClass(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
