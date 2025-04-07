package com.example.demo;



import com.example.demo.controller.CartController;
import com.example.demo.entities.Item;
import com.example.demo.services.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @Test
    void addItemToCart() {
        Item item = new Item("itemId", "Item Name", 10.0, 1);
        String userId = "userId";

        when(cartService.addItemToCart(userId, item)).thenReturn(item);

        ResponseEntity<String> response = cartController.addItemToCart(item, userId);

        assertEquals(ResponseEntity.ok("Item added to cart."), response);
        verify(cartService, times(1)).addItemToCart(userId, item);
    }

    @Test
    void viewCart() {
        String userId = "userId";
        List<Item> items = new ArrayList<>();
        items.add(new Item("itemId", "Item Name", 10.0, 1));

        when(cartService.getAllItemsInCart(userId)).thenReturn(items);

        ResponseEntity<?> response = cartController.viewCart(userId);

        assertEquals(ResponseEntity.ok(items), response);
        verify(cartService, times(1)).getAllItemsInCart(userId);
    }

    @Test
    void removeItemFromCart() {
        String itemId = "itemId";
        String userId = "userId";

        when(cartService.removeItemFromCart(userId, itemId)).thenReturn(true);

        ResponseEntity<String> response = cartController.removeItemFromCart(new Item(itemId, null, 0, 0), userId);

        assertEquals(ResponseEntity.ok("Item removed from cart."), response);
        verify(cartService, times(1)).removeItemFromCart(userId, itemId);
    }

}
