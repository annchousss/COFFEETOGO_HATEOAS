package ru.itis.coffeeservice.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.coffeeservice.entries.InfoUsers;
import ru.itis.coffeeservice.entries.InfoUsersResponse;

@Component
public class InfoInfoUsersApiClientWebClientImpl implements InfoUsersClient {

    private WebClient client;

    public InfoInfoUsersApiClientWebClientImpl(@Value("${network.users.url}") String url) {
        client = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                        .build())
                .baseUrl(url)
                .build();
    }

    @Override
    public Flux<InfoUsers> getAll() {
        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(InfoUsersResponse.class))
                .flatMapIterable(InfoUsersResponse::getItems)
                .map(record ->
                        InfoUsers.builder()
                                .name(record.getName())
                                .uniqueUrlName(record.getUniqueUrlName())
                                .from("UsersInfoAPI")
                                .status(record.getStatus())
                                .build());
    }
}
