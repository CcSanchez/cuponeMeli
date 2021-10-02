package com.co.cupones.cupones.service.implementation;


import com.co.cupones.cupones.repository.FavoritosRepository;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RunWith(MockitoJUnitRunner.class)
class FavoriteItemServiceTest {

    @InjectMocks
    private FavoriteItemService favoriteItemService = new FavoriteItemService();

    @Test
    void getFavoriteItems() {
        // Poblando datos
        List<String> items_id = new ArrayList<>();
        items_id.add("MLA816019440");
        items_id.add("MLA811601010");
        items_id.add("MLA919368645");
        items_id.add("MLA932252932");
        items_id.add("MLA886405818");
        items_id.add("MLA913010868");
        FavoritosRepository.addFavorite(items_id);

        // Obteniendo respuesta esperada
        List<Map<String, Integer>> listPrueba = new ArrayList<>();
        Map<String, Integer> prueba = new HashMap<>();
        prueba.put("MLA816019440", 3);
        prueba.put("MLA811601010", 3);
        prueba.put("MLA919368645", 3);
        prueba.put("MLA932252932", 3);
        prueba.put("MLA886405818", 2);
        listPrueba.add(prueba);
        List<Map<String, Integer>> list = favoriteItemService.getFavoriteItems();
        Assertions.assertArrayEquals(listPrueba.toArray(),list.toArray());
        //Assert.assertTrue(true);
    }


}