package ru.itis.coffeeservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.coffeeservice.services.PdfServiceImpl;

@RestController
public class PdfController {

    @Autowired
    private PdfServiceImpl pdfService;

    @GetMapping(value = "/download-pic")
    public String getDownL(@RequestParam(value = "item") String url) {
        pdfService.downloadPic(url);
        return "The item is downloaded successfully!";
    }

}
