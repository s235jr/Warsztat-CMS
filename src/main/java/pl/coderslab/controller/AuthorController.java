package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.Author;
import pl.coderslab.repository.AuthorRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping("/authors")
    public String showAllAuthors(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authorList";
    }

    @RequestMapping("/addauthor")
    public String addAuthor(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "authorForm";
    }

    @RequestMapping(value = "/addauthor", method = RequestMethod.POST)
    public String saveAuthor(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "authorForm";
        }
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @RequestMapping("/delauthor/{id}")
    public String delAuthor(@PathVariable Long id, RedirectAttributes attributes) {
        authorRepository.delete(id);
        attributes.addFlashAttribute("infomessage", "Wiersz został usunięty");
        return "redirect:/authors";
    }

    @RequestMapping("/editauthor/{id}")
    public String editAuthor(Model model, @PathVariable Long id) {
        Author author = authorRepository.findOne(id);
        model.addAttribute("author", author);
        return "authorForm";
    }

    @RequestMapping(value = "/editauthor/{id}", method = RequestMethod.POST)
    public String saveEditAuthor(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "authorForm";
        }
        authorRepository.save(author);
        return "redirect:/authors";
    }

}
