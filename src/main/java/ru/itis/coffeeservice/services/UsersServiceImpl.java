package ru.itis.coffeeservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.itis.coffeeservice.clients.InfoUsersClient;
import ru.itis.coffeeservice.entries.InfoUsers;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private List<InfoUsersClient> clients;

    @Override
    public Flux<InfoUsers> getAll() {
        List<Flux<InfoUsers>> fluxes = clients.stream().map(this::getAll).collect(Collectors.toList());
        return Flux.merge(fluxes);
    }

    private Flux<InfoUsers> getAll(InfoUsersClient client) {
        return client.getAll().subscribeOn(Schedulers.elastic());
    }
}
