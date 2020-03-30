package ru.itis.coffeeservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.coffeeservice.services.CategoryService;

@RepositoryRestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categories/{category-id}/remove", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> removeProducts(@PathVariable("category-id") Long categoryId) {
        return ResponseEntity.ok(
                new EntityModel<>(
                        categoryService.removeAllDeletedProducts(categoryId)));
    }
}
