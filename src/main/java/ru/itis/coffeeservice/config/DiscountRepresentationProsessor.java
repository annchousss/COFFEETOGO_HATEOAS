package ru.itis.coffeeservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.coffeeservice.controllers.ProductController;
import ru.itis.coffeeservice.models.Discount;
import ru.itis.coffeeservice.models.DiscountStatus;
import ru.itis.coffeeservice.models.Product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DiscountRepresentationProsessor implements RepresentationModelProcessor<EntityModel<Discount>> {

    @Autowired
    private RepositoryEntityLinks links;


    @Override
    public EntityModel<Discount> process(EntityModel<Discount> model) {
        Discount d = model.getContent();
        if (d != null && d.getStatus().equals(DiscountStatus.EXPIRED)) {
            model.add(linkTo(methodOn(ProductController.class).publish(d.getId())).withRel("extend"));
        }
        return model;
    }
}
