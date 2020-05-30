package ru.itis.coffeeservice.services;

import reactor.core.publisher.Flux;
import ru.itis.coffeeservice.entries.InfoUsers;


public interface UsersService {
    Flux<InfoUsers> getAll();
}
