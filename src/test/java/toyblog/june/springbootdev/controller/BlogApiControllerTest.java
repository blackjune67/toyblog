package toyblog.june.springbootdev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import toyblog.june.springbootdev.domain.Article;
import toyblog.june.springbootdev.domain.User;
import toyblog.june.springbootdev.dto.AddArticleRequest;
import toyblog.june.springbootdev.dto.record.UpdateArticleRequest;
import toyblog.june.springbootdev.repository.BlogRepository;
import toyblog.june.springbootdev.repository.UserRepository;

import java.security.Principal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
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
    @Autowired
    UserRepository userRepository;
    User user;


    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        blogRepository.deleteAll();
    }

    @BeforeEach
    void setSecurityContext() {
        userRepository.deleteAll();
        user = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("a1234")
                .build());

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }

    private Article createDefultArticle() {
        return blogRepository.save(Article
                .builder()
                .title("제목")
                .author(user.getUsername())
                .content("내용")
                .build());
    }

    @DisplayName("블로그 글 추가에 성공")
    @Test
    public void test01() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final String author = "author";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content, author);
        final String reqBody = objectMapper.writeValueAsString(userRequest);

        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("username");

        // when
        ResultActions result = mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .principal(principal)
                        .content(reqBody));

        // then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
        assertThat(articles.get(0).getCreatedAt()).isNotNull();
    }

    @Test
    @DisplayName("블로그 글 조회에 성공")
    public void test02() throws Exception {
        // given
        final String url = "/api/articles";
        /*final String title = "title";
        final String content = "content";*/
        Article savedArticle = createDefultArticle();

        /*blogRepository.save(Article
                .builder()
                .title(title)
                .content(content)
                .build());*/
        // when
        final ResultActions resultActions = mockMvc.perform(get(url).accept(APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$[0].content").value(savedArticle.getContent()));
    }

    @Test
    @DisplayName("블로그 글 조회 성공, 단건")
    public void test03() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        Article savedArticle = createDefultArticle();
        // when
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$.content").value(savedArticle.getContent()));
    }

    @Test
    @DisplayName("블로그 글 삭제 (단건)")
    public void test04() throws Exception {
        final String url = "/api/articles/{id}";
        Article savedArticle = createDefultArticle();

        // when
        mockMvc.perform(delete(url, savedArticle.getId()))
                .andExpect(status().isOk());

        // then
        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();
    }

    @Test
    @DisplayName("블로그 글 수정")
    public void test05() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        Article savedArticle = createDefultArticle();


        final String newTitle = """
                안녕하세요 제 사연입니다.
                """;
        final String newContent = """
                이 편지는 오래 전 대항해시대때부터 존재해왔던 글로써...
                """;
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest(newTitle, newContent);

        // when
        ResultActions resultActions = mockMvc.perform(put(url, savedArticle.getId())
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(updateArticleRequest)))
                .andExpect(status().isOk());

        // then
        Article article = blogRepository.findById(savedArticle.getId()).orElseThrow(IllegalArgumentException::new);

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }
}