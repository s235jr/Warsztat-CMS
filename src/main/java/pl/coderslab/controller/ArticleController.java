package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Category;
import pl.coderslab.repository.ArticleRepository;
import pl.coderslab.repository.AuthorRepository;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.validator.ValidationArticle;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Controller
public class ArticleController {


    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @ModelAttribute("authors")
    public List<Author> getPublisher() {
        return authorRepository.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @RequestMapping("/articles")
    public String showAllArticles(Model model) {
        //List<Article> articles = articleRepository.findArticlesByDraftIsFalse();
        List<Article> articles = articleRepository.findArticleWithCategories();    // nie wymaga EAGER na FetchType
        model.addAttribute("articles", articles);
        for(Article article : articles) {
            System.out.println(article);
        }
        model.addAttribute("categories", categoryRepository.findAll());
        return "articleList";
    }

    @RequestMapping("/addarticle")
    public String addArticle(Model model) {
        Article article = new Article();
        model.addAttribute("article", article);
        return "articleForm";
    }

    @RequestMapping(value = "/addarticle", method = RequestMethod.POST)
    public String saveArticle(@Validated({ValidationArticle.class}) Article article, BindingResult result) {
        if (result.hasErrors()) {
            return "articleForm";
        }
        article.setCreated(new Timestamp(System.currentTimeMillis()));
        articleRepository.save(article);
        return "redirect:/articles";
    }

    @RequestMapping("/delarticle/{id}")
    public String delArticle(@PathVariable Long id, RedirectAttributes attributes) {
        articleRepository.delete(id);
        attributes.addFlashAttribute("infomessage", "Wiersz został usunięty");
        return "redirect:/articles";
    }

    @RequestMapping("/editarticle/{id}")
    public String editArticle(Model model, @PathVariable Long id) {
        Article article = articleRepository.getArticleWithCategoriesById(id);
        model.addAttribute("article", article);
        return "articleForm";
    }

    @RequestMapping(value = "/editarticle/{id}", method = RequestMethod.POST)
    public String saveEditArticle(@Validated({ValidationArticle.class}) Article article, BindingResult result) {
        if (result.hasErrors()) {
            return "articleForm";
        }
        article.setCreated(articleRepository.findOne(article.getId()).getCreated());
        article.setUpdated(new Timestamp(System.currentTimeMillis()));
        articleRepository.save(article);
        return "redirect:/articles";
    }

    @RequestMapping("/articles/{id}")
    public String showArticleByCategory(@PathVariable long id, Model model) {
        Category category = categoryRepository.findOne(id);
        //List<Article> articleByCategories = articleRepository.findArticleByCategories(Arrays.asList(category)); //todo
        List<Article> articleByCategories = articleRepository.getArticleByCategories(category);
        System.out.println(articleByCategories);
        model.addAttribute("articles", articleByCategories);
        return "articleList";
    }
}
