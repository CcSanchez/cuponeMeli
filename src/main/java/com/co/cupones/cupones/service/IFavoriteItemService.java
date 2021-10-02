package com.co.cupones.cupones.service;

import java.util.List;
import java.util.Map;

public interface IFavoriteItemService {

    /**
     * Metodo que obtiene los items favoritos y depura los mas favoriteados
     *
     * @return lista de elementos depurados
     */
    List<Map<String, Integer>> getFavoriteItems();
}
