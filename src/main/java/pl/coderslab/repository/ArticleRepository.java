package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Category;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleInterface {

    List<Article> findArticleByCategories(List<Category> category);

    List<Article> findArticlesByDraftIsTrue();

    List<Article> findArticlesByDraftIsFalse();

    @Query("SELECT a FROM Article a JOIN FETCH a.categories WHERE a.id in ( ?1 )")
    Article getArticleWithCategoriesById(Long id);

    @Query("Select DISTINCT  a FROM Article a JOIN FETCH a.categories ORDER BY a.id")
    List<Article> findArticleWithCategories();

    @Query("SELECT a FROM Article a JOIN FETCH a.categories c WHERE c in ( ?1 )")
    List<Article> getArticleByCategories(Category category);

}
