package com.co.cupones.cupones.service.implementation;

import com.co.cupones.cupones.models.FavoritsDto;
import com.co.cupones.cupones.repository.FavoritosRepository;
import com.co.cupones.cupones.service.IFavoriteItemService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FavoriteItemService implements IFavoriteItemService {

    /**
     * Metodo que obtiene los items favoritos y depura los mas favoriteados
     *
     * @return lista de elementos depurados
     */
    @Override
    public List<Map<String, Integer>> getFavoriteItems() {
        List<Map<String, Integer>> itemsList = new ArrayList<>();
        List<FavoritsDto> f1 = FavoritosRepository.getTopFavorites().stream().sorted(Comparator.comparing(FavoritsDto::getFavoriteCount).reversed()).limit(5)
                .collect(Collectors.toList());
        itemsList.add(f1.stream().collect(Collectors.toMap(FavoritsDto::getId, FavoritsDto::getFavoriteCount)));
        return itemsList;
    }
}
