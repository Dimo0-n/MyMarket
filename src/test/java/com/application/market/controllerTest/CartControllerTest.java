package com.application.market.controllerTest;

import com.application.market.controller.CartController;
import com.application.market.service.CartService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testAddToCart_LoggedIn() throws Exception {
        // Simulează autentificarea
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user@example.com");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Simulează cererea POST către /addToCart/{productId}
        mockMvc.perform(post("/addToCart/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"quantity\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Produsul a fost adăugat în coș!"))
                .andExpect(jsonPath("$.messageType").value("success"));
    }

    @Test
    public void testAddToCart_AnonymousUser() throws Exception {
        // Simulează utilizatorul anonim
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("anonymousUser");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Simulează cererea POST către /addToCart/{productId}
        mockMvc.perform(post("/addToCart/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"quantity\": 1}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Loghează-te pentru a adăuga produse în coș!"))
                .andExpect(jsonPath("$.messageType").value("error"));
    }
}

