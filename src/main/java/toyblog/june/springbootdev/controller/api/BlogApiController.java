package toyblog.june.springbootdev.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyblog.june.springbootdev.domain.Article;
import toyblog.june.springbootdev.dto.record.AddArticleRequest2;
import toyblog.june.springbootdev.dto.record.ArticleResponse;
import toyblog.june.springbootdev.dto.record.UpdateArticleRequest;
import toyblog.june.springbootdev.service.BlogService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    // * 글 쓰기
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest2 request) {
//        Article savedArticle = blogService.save(request);
        Article savedArticle = blogService.save2(request); // * record 사용
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    // * 글 목록 조회
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> findAllArticles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity
                .ok()
                .body(findAllArticles);
    }

    // * 글 목록 조희 (단건)
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findByArticle(@PathVariable("id") long id) {
        Article findByArticle = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(findByArticle));
    }

    // * 글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    // * 글 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id, @RequestBody UpdateArticleRequest request) {
        Article updateByArticle = blogService.update(id, request);
        return ResponseEntity.ok().body(updateByArticle);
    }
}
