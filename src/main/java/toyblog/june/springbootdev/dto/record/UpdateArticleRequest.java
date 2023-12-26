package toyblog.june.springbootdev.dto.record;

public record UpdateArticleRequest(String title, String content) {
    public UpdateArticleRequest updateArticleRequest(String title, String content) {
        return new UpdateArticleRequest(title, content);
    }
}
