package nl.maikel.mu_bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.maikel.mu_bank.mapper.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MuBankApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void givenCorrectInputWhenCreateAccountThenCreated() throws Exception {
        mockMvc.perform(post
                        ("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new AccountDTO("8dd4b430-475f-4303-b01c-28aec517646e", null, BigDecimal.ZERO))))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void givenIncorrectInputWhenCreateAccountThenBadRequest() throws Exception {
        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new AccountDTO(null, null,null))))
                .andExpect(jsonPath("$.httpStatusCode", is(400)))
                .andExpect(jsonPath("$.httpStatus", is("Bad Request")))
                .andExpect(jsonPath("$.message", is("Customer ID must not be null")))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
