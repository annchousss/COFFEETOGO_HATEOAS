package ru.itis.coffeeservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.coffeeservice.models.Category;
import ru.itis.coffeeservice.services.CategoryService;
import ru.itis.coffeeservice.services.ProductService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CategoryTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        when(categoryService.removeAllDeletedProducts(1L)).thenReturn(clearCategory());
    }

    @Test
    public void testRemoveProductsFromCategory() throws Exception {
        mockMvc.perform(delete("/categories/1/remove"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(clearCategory().getName()))
                .andExpect(jsonPath("$.description").value(clearCategory().getDescription()))
                .andDo(document("remove_deleted_products", responseFields(
                        fieldWithPath("name").description("Name of the category"),
                        fieldWithPath("description").description("Description of the category")
                )));
    }

    private Category clearCategory() {
        return Category.builder()
                .id(1L)
                .name("Snacks")
                .description("Snacks for coffee")
                .products(Collections.emptyList())
                .build();
    }
}
