package com.sharify.controllers;

import com.sharify.models.Product;
import com.sharify.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/category")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping()
    public String home() {
        return "redirect:/";
    }

    @GetMapping("/{category}")
    public String homeStuff(@PathVariable String category, Model model) {
        Iterable<Product> products;
        if (!category.equals("all"))
            products = productRepo.findByCategory(category);
        else
            products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/add")
    public String add(Model model) {
        return "add";
    }

    @PostMapping("/product/add")
    public String add(@RequestParam String title, @RequestParam Float price,
                      @RequestParam String category, @RequestParam Float bail,
                      @RequestParam String seller, @RequestParam String address,
                      @RequestParam String city, @RequestParam Integer phoneNumber,
                      @RequestParam String email) {
        Product product = new Product(category, title, price, bail, seller, address, city, phoneNumber, email);
        productRepo.save(product);
        return "redirect:/category/all";
    }

    @PostMapping("/product/{id}")
    public String delete(@PathVariable Long id) {
        productRepo.deleteById(id);
        return "redirect:/category/all";
    }

    @PostMapping("/{category}")
    public String search(@RequestParam String search, Model model, @PathVariable String category) {
        search = search.trim();
        if (!search.equals("")) {
            List<Product> products = new ArrayList<>();
            Pattern pattern = Pattern.compile(".*" + search + ".*");
            for (Product product : (category.equals("all")) ? productRepo.findAll() : productRepo.findByCategory(category)) {
                Matcher matcher = pattern.matcher(product.getTitle());
                boolean matchFound = matcher.find();
                if (matchFound)
                    products.add(product);
            }
            model.addAttribute("products", products);
        }
        return "products";
    }
}
