package com.co.cupones.cupones.controllers;

import com.co.cupones.cupones.models.CuponesInDto;
import com.co.cupones.cupones.models.CuponesOutDto;
import com.co.cupones.cupones.repository.FavoritosRepository;
import com.co.cupones.cupones.service.ICuponesService;
import com.co.cupones.cupones.service.IFavoriteItemService;
import com.co.cupones.cupones.utilities.Constantes;
import com.co.cupones.cupones.utilities.exceptions.ErrorGeneralException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constantes.COUPON)
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Slf4j
public class CuponesController {

    @Autowired
    ICuponesService service;

    @Autowired
    IFavoriteItemService favoriteItemService;

    @GetMapping(Constantes.STATS)
    @ApiOperation(value = "Servicio que retorna los 5 productos mas favoriteados")
    public List<Map<String, Integer>> getFavoriteProducts() {
        return favoriteItemService.getFavoriteItems();
    }


    @PostMapping
    @ApiOperation(value = "Servicio que permite el calculo de los productos favoritos que se ajusten al valor del bono ")
    public ResponseEntity<CuponesOutDto> cupones(
            @ApiParam(name = "CuponesInDto", type = "CuponesInDto", value = "Dto con los item favoritos y el monto del bono", example = "{\n" +
                    "\"item_ids\": [\"MLA1\", \"MLA2\", \"MLA3\", \"MLA4\", \"MLA5\"],\n" +
                    "\"amount\": 500\n" +
                    "}", required = true) @Valid @RequestBody CuponesInDto cuponesInDto
    ) throws ErrorGeneralException {
        try {
            return ResponseEntity.ok(service.obtenerProductos(cuponesInDto));
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new ErrorGeneralException(e);
        }
    }
}
