package ru.itis.coffeeservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.coffeeservice.services.ImgServiceImpl;

@RestController
public class ImgController {

    @Autowired
    private ImgServiceImpl imgService;

    @GetMapping(value = "/download-pic")
    public String getDownL(@RequestParam(value = "item") String url) {
        imgService.downloadPic(url);
        return "The item is downloaded successfully!";
    }

}
