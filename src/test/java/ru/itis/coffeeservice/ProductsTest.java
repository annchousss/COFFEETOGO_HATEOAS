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
import ru.itis.coffeeservice.models.Product;
import ru.itis.coffeeservice.models.ProductStatus;
import ru.itis.coffeeservice.services.ProductService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ProductsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @BeforeEach
    public void setUp() {
        when(productService.post(1L)).thenReturn(postedProduct());
    }

    @Test
    public void productPublishTest() throws Exception {
        mockMvc.perform(put("/products/1/publish")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(postedProduct().getName()))
                .andExpect(jsonPath("$.description").value(postedProduct().getDescription()))
                .andExpect(jsonPath("$.price").value(postedProduct().getPrice()))
                .andExpect(jsonPath("$.status").value(postedProduct().getStatus().toString()))
                .andDo(document("post_product", responseFields(
                        fieldWithPath("name").description("Name of the product"),
                        fieldWithPath("description").description("Description of the product"),
                        fieldWithPath("status").description("Status of the product"),
                        fieldWithPath("price").description("Price of the product")
                )));

    }

    private Product postedProduct() {
        return Product.builder()
                .id(1L)
                .name("Coffee Moccachino")
                .description("qwerty")
                .status(ProductStatus.DELETED)
                .price(3)
                .build();
    }

}
