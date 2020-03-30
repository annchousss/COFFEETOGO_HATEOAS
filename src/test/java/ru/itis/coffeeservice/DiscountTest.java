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
import ru.itis.coffeeservice.models.Discount;
import ru.itis.coffeeservice.models.DiscountStatus;
import ru.itis.coffeeservice.services.DiscountService;

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
public class DiscountTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DiscountService discountService;

    @BeforeEach
    public void setUp() {
        when(discountService.extendCard(1L)).thenReturn(extendedDiscount());
    }

    @Test
    public void discountExtendTest() throws Exception {
        mockMvc.perform(put("/discounts/1/extend")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ordersNumber").value(extendedDiscount().getOrdersNumber()))
                .andExpect(jsonPath("$.discountPercent").value(extendedDiscount().getDiscountPercent()))
                .andExpect(jsonPath("$.status").value(extendedDiscount().getStatus().toString()))
                .andDo(document("extend_discount", responseFields(
                        fieldWithPath("ordersNumber").description("Number of customer's orders"),
                        fieldWithPath("discountPercent").description("Percentage discount"),
                        fieldWithPath("status").description("Status of the discount card")
                )));
    }

    private Discount extendedDiscount() {
        return Discount.builder()
                .id(1L)
                .ordersNumber(10)
                .discountPercent(5)
                .status(DiscountStatus.VALID)
                .build();
    }

}


