package com.example.demo;



import com.example.demo.entities.Cart;
import com.example.demo.entities.Item;
import com.example.demo.repository.CartRepository;
import com.example.demo.services.CartServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    void addItemToCart() {
        String userId = "userId";
        Item item = new Item("itemId", "Item Name", 10.0, 1);
        Cart existingCart = new Cart();
        when(cartRepository.findByUserId(userId)).thenReturn(existingCart);

        Item result = cartService.addItemToCart(userId, item);

        assertEquals(item, result);
        assertEquals(1, existingCart.getItems().size());
        verify(cartRepository, times(1)).save(existingCart);
    }

    

  

    @Test
    void getAllItemsInCart() {
        String userId = "userId";
        List<Item> items = new ArrayList<>();
        items.add(new Item("itemId", "Item Name", 10.0, 1));
        Cart existingCart = new Cart();
        existingCart.setItems(items);
        when(cartRepository.findByUserId(userId)).thenReturn(existingCart);

        List<Item> result = cartService.getAllItemsInCart(userId);

        assertEquals(items, result);
    }
}
