package com.greenfrog.restapi.common;

import com.greenfrog.restapi.index.IndexController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.Errors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ErrorsResource extends EntityModel<Errors> {
    public ErrorsResource(Errors erros, Link... links) {
        super(erros, links);
        add(linkTo(methodOn(IndexController.class).index()).withRel("Index"));
    }
}
