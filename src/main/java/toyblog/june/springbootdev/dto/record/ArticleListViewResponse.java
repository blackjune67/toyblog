package toyblog.june.springbootdev.dto.record;

import toyblog.june.springbootdev.domain.Article;

public record ArticleListViewResponse(Long id, String title, String content) {
    public ArticleListViewResponse(Article article) {
        this(article.getId(), article.getTitle(), article.getContent());
    }
}
