package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Category;
import pl.coderslab.repository.ArticleRepository;
import pl.coderslab.repository.AuthorRepository;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.validator.ValidationDraft;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class DraftController {


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

    @RequestMapping("/drafts")
    public String showAllArticles(Model model) {
        List<Article> articles = articleRepository.findArticlesByDraftIsTrue();
        model.addAttribute("articles", articles);
        return "draftList";
    }

    @RequestMapping("/adddraft")
    public String addDraft(Model model) {
        Article article = new Article();
        model.addAttribute("article", article);
        return "draftForm";
    }

    @RequestMapping(value = "/adddraft", method = RequestMethod.POST)
    public String saveArticle(@Validated({ValidationDraft.class}) Article article, BindingResult result) {
        if (result.hasErrors()) {
            return "draftForm";
        }
        article.setCreated(new Timestamp(System.currentTimeMillis()));
        article.setDraft(true);
        articleRepository.save(article);
        return "redirect:/drafts";
    }

    @RequestMapping("/deldraft/{id}")
    public String delDraft(@PathVariable Long id) {
        articleRepository.delete(id);
        return "redirect:/drafts";
    }

    @RequestMapping("/editdraft/{id}")
    public String editArticle(Model model, @PathVariable Long id) {
        Article article = articleRepository.findOne(id);
        model.addAttribute("article", article);
        return "draftForm";
    }

    @RequestMapping(value = "/editdraft/{id}", method = RequestMethod.POST)
    public String saveEditArticle(@Validated({ValidationDraft.class}) Article article, BindingResult result) {
        if (result.hasErrors()) {
            return "draftForm";
        }
        article.setCreated(articleRepository.findOne(article.getId()).getCreated());
        article.setUpdated(new Timestamp(System.currentTimeMillis()));
        article.setDraft(false);
        articleRepository.save(article);
        return "redirect:/drafts";
    }

}
