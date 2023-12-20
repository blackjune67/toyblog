package toyblog.june.springbootdev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import toyblog.june.springbootdev.domain.Article;
import toyblog.june.springbootdev.dto.AddArticleRequest;
import toyblog.june.springbootdev.dto.UpdateArticleRequest;
import toyblog.june.springbootdev.repository.BlogRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    BlogRepository blogRepository;


    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("블로그 글 추가에 성공")
    @Test
    public void test01() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);
        final String reqBody = objectMapper.writeValueAsString(userRequest);

        // when
        ResultActions result = mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(reqBody));

        // then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("블로그 글 조회에 성공")
    public void test02() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article
                .builder()
                .title(title)
                .content(content)
                .build());
        // when
        final ResultActions resultActions = mockMvc.perform(get(url).accept(APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].content").value(content));
    }

    @Test
    @DisplayName("블로그 글 조회 성공")
    public void test03() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article saveArticle = blogRepository.save(
                Article.builder()
                        .title(title)
                        .content(content)
                        .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, saveArticle.getId()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }

    @Test
    @DisplayName("블로그 글 삭제 (단건)")
    public void test04() throws Exception {
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article saveArticle = blogRepository.save(
                Article.builder()
                        .title(title)
                        .content(content)
                        .build());

        // when
        mockMvc.perform(delete(url, saveArticle.getId())).andExpect(status().isOk());

        // then
        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();
    }

    @Test
    @DisplayName("블로그 글 수정")
    public void test05() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article saveArticle = blogRepository.save(
                Article.builder()
                        .title(title)
                        .content(content)
                        .build());

        final String newTitle = """
                안녕하세요 제 사연입니다.
                """;
        final String newContent = """
                이 편지는 오래 전 대항해시대때부터 존재해왔던 글로써...
                """;
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest(newTitle, newContent);

        // when
        ResultActions resultActions = mockMvc.perform(put(url, saveArticle.getId())
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(updateArticleRequest)))
                .andExpect(status().isOk());

        // then
        Article article = blogRepository.findById(saveArticle.getId()).orElseThrow(IllegalArgumentException::new);

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }
}