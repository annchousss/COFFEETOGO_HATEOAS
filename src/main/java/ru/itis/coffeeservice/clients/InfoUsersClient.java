package ru.itis.coffeeservice.clients;

import reactor.core.publisher.Flux;
import ru.itis.coffeeservice.entries.InfoUsers;

public interface InfoUsersClient {
    Flux<InfoUsers> getAll();
}
