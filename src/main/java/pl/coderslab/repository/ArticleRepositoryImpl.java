package pl.coderslab.repository;


import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleInterface {

    @Autowired
    EntityManager entityManager;

    @Override
    public void metodaTestowa(List<Category> categoryList) {
        //todo

    }
}
