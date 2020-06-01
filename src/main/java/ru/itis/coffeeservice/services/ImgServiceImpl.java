package ru.itis.coffeeservice.services;

import org.springframework.stereotype.Service;
import ru.itis.coffeeservice.rabbitmq.Client;

@Service
public class ImgServiceImpl implements ImgService {

    public void downloadPic(String item) {
        Client client = new Client();
        switch (item) {
            case "latte":
                item = "https://i.pinimg.com/474x/3a/1b/0c/3a1b0c9482c57e103fecd49631e2d617.jpg";
                break;
            case "cappuccino":
                item = "https://xindaorussia.ru/800x800xffffff/i/cdn/image/p432.691__b_1.jpg";
                break;
            case "tea":
                item = "https://vsednr.ru/wp-content/uploads/2018/11/kofe.jpg";
                break;
            case "cookie":
                item = "https://www.abc.net.au/cm/rimage/10090618-3x4-xlarge.jpg";
                break;
        }
        client.downloadPicture(item);
    }
}