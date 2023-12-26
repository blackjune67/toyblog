package toyblog.june.springbootdev.dto.record;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import toyblog.june.springbootdev.domain.Article;

import java.time.LocalDateTime;
import java.util.Objects;

public record ArticleViewResponse(Long id, String title, @Nullable String content, LocalDateTime createdAt) {

    public ArticleViewResponse(Article article) {
        this(article.getId(), article.getTitle(), article.getContent(), article.getCreatedAt());
    }

    // 기본 생성자 추가
    public ArticleViewResponse() {
        this(0L, "", null, LocalDateTime.now());
    }
}