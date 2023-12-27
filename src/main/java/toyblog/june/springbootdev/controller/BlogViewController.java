package toyblog.june.springbootdev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import toyblog.june.springbootdev.domain.Article;
import toyblog.june.springbootdev.dto.record.ArticleListViewResponse;
import toyblog.june.springbootdev.dto.ArticleViewResponse;
import toyblog.june.springbootdev.service.BlogService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticle(Model model) {
        List<ArticleListViewResponse> articleResponses = blogService.findAll().stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles", articleResponses);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article findById = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(findById));
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(name="id", required = false) Long id, Model model) {
        if(id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article findById = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(findById));
        }
        return "newArticle";
    }
}
