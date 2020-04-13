package ru.marina.napi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NapiProxyController.class)
class NapiProxyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NapiProxyService napiProxyService;

    @Test
    void call() throws Exception {
        when(napiProxyService.call(eq("clazz"), any())).thenReturn("hello");
        mockMvc.perform(get("/call")
                .param("clazz", "clazz")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }
}