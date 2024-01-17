package toyblog.june.springbootdev.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyblog.june.springbootdev.domain.Article;
import toyblog.june.springbootdev.dto.AddArticleRequest;
import toyblog.june.springbootdev.dto.UpdateArticleRequest2;
import toyblog.june.springbootdev.dto.record.AddArticleRequest2;
import toyblog.june.springbootdev.dto.record.UpdateArticleRequest;
import toyblog.june.springbootdev.repository.BlogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogService {
    private final BlogRepository blogRepository;

    // * class 사용
    public Article save(AddArticleRequest request, String userName) {
        return blogRepository.save(request.toEntity(userName));
    }

    // * record 사용
    public Article save2(AddArticleRequest2 request, String userName) {
        return blogRepository.save(request.toEntity(userName));
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found :" + id));

        authorizeArticleAuthor(article);
        blogRepository.deleteById(id);
    }

    // * class 사용
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        article.update(request.title(), request.content());

        return article;
    }

    // * record 사용
    @Transactional
    public Article update2(long id, UpdateArticleRequest2 request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("article userName = " + article.getAuthor());
        log.info("userName = " + userName);
        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
