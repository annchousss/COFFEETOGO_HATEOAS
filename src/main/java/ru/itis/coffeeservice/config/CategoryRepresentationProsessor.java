package ru.itis.coffeeservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.coffeeservice.controllers.ProductController;
import ru.itis.coffeeservice.models.Category;
import ru.itis.coffeeservice.models.Product;
import ru.itis.coffeeservice.models.ProductStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryRepresentationProsessor implements RepresentationModelProcessor<EntityModel<Category>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Category> process(EntityModel<Category> model) {
        Category category = model.getContent();
        assert category != null;
        for (Product p : category.getProducts()) {
            if (p.getStatus().equals(ProductStatus.DELETED)) {
                model.add(linkTo(methodOn(ProductController.class).publish(category.getId())).withRel("remove"));
                break;
            }
        }
        return model;
    }
}
