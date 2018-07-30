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
import pl.coderslab.entity.Category;
import pl.coderslab.repository.CategoryRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/categories")
    public String showAllCategories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categoryList";
    }

    @RequestMapping("/addcategory")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categoryForm";
    }

    @RequestMapping(value = "/addcategory", method = RequestMethod.POST)
    public String saveCategory(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "categoryForm";
        }
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @RequestMapping("/delcategory/{id}")
    public String delCategory(@PathVariable Long id, RedirectAttributes attributes) {
        categoryRepository.delete(id);
        attributes.addFlashAttribute("infomessage", "Wiersz został usunięty");
        return "redirect:/categories";
    }

    @RequestMapping("/editcategory/{id}")
    public String editAuthor(Model model, @PathVariable Long id) {
        Category category = categoryRepository.findOne(id);
        model.addAttribute("category", category);
        return "categoryForm";
    }

    @RequestMapping(value = "/editcategory/{id}", method = RequestMethod.POST)
    public String saveEditAuthor(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "categoryForm";
        }
        categoryRepository.save(category);
        return "redirect:/categories";
    }


}
