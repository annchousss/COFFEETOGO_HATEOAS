package ru.itis.coffeeservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.coffeeservice.controllers.ProductController;
import ru.itis.coffeeservice.models.Product;
import ru.itis.coffeeservice.models.ProductStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Product>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Product> process(EntityModel<Product> model) {
        Product product = model.getContent();
        if (product != null && product.getStatus().equals(ProductStatus.DRAFT)) {
            model.add(linkTo(methodOn(ProductController.class).publish(product.getId())).withRel("publish"));
        }

        if (product!= null && product.getStatus().equals(ProductStatus.PUBLISHED)) {
            model.add(links.linkToItemResource(Product.class, product.getId()).withRel("delete"));
        }
        return model;
    }
}
