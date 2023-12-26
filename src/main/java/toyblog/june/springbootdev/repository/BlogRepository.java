package toyblog.june.springbootdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyblog.june.springbootdev.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
