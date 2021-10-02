package com.co.cupones.cupones.repository;

import com.co.cupones.cupones.models.FavoritsDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class FavoritosRepository {
    /**
     * Variable en la cual se almacenarn los favoritos
     */
    public static List<FavoritsDto> favoritos = new ArrayList<>();

    /**
     * Metodo que añane un favorito a la lista
     *
     * @param items elementos favoritos recibidos
     */
    public static void addFavorite(List<String> items) {
        items.forEach(item -> {
            Optional<FavoritsDto> favorito1 = favoritos.stream().filter(favorito -> favorito.getId().equals(item)).findFirst();
            if (favorito1.isPresent()) {
                favorito1.get().setFavoriteCount(favorito1.get().getFavoriteCount() + 1);
            } else {
                favoritos.add(new FavoritsDto(item, 1));
            }
        });
    }

    /**
     * Metodo que retorna un favorito
     *
     * @return Lista de favoritos
     */
    public static List<FavoritsDto> getTopFavorites() {
        return favoritos;
    }
}
