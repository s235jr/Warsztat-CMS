package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Article;
import pl.coderslab.repository.ArticleRepository;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    ArticleRepository articleRepository;

    @RequestMapping("/")
    public String home(Model model){
        List<Article> articles = articleRepository.findAll();
        System.out.println(articles.toString());
        model.addAttribute("articles", articles);
        return "articleList";
    }

}
