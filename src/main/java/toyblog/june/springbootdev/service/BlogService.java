package toyblog.june.springbootdev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyblog.june.springbootdev.domain.Article;
import toyblog.june.springbootdev.dto.AddArticleRequest;
import toyblog.june.springbootdev.repository.BlogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest addArticleRequest) {
        return blogRepository.save(addArticleRequest.toEntity());
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
}
