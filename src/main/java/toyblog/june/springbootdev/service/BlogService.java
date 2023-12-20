package toyblog.june.springbootdev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyblog.june.springbootdev.domain.Article;
import toyblog.june.springbootdev.dto.AddArticleRequest;
import toyblog.june.springbootdev.dto.AddArticleRequest2;
import toyblog.june.springbootdev.dto.UpdateArticleRequest;
import toyblog.june.springbootdev.dto.UpdateArticleRequest2;
import toyblog.june.springbootdev.repository.BlogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    // * class 사용
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // * record 사용
    public Article save2(AddArticleRequest2 request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    // * class 사용
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        article.update(request.title(), request.content());
        return article;
    }

    // * record 사용
    @Transactional
    public Article update2(long id, UpdateArticleRequest2 request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
