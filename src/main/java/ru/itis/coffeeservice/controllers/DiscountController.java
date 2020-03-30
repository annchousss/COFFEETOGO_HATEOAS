package ru.itis.coffeeservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.coffeeservice.services.DiscountService;

@RepositoryRestController
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @RequestMapping(value = "/discounts/{discount-id}/extend", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> extendCard(@PathVariable("discount-id") Long discountId) {
        return ResponseEntity.ok(
                new EntityModel<>(
                        discountService.extendCard(discountId)));
    }
}
