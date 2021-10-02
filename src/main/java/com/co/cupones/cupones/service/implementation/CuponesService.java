package com.co.cupones.cupones.service.implementation;

import com.co.cupones.cupones.clients.IPricesClient;
import com.co.cupones.cupones.models.CuponesInDto;
import com.co.cupones.cupones.models.CuponesOutDto;
import com.co.cupones.cupones.models.PriceDto;
import com.co.cupones.cupones.models.ResponseApiDto;
import com.co.cupones.cupones.service.ICuponesService;
import com.co.cupones.cupones.utilities.Constantes;
import com.co.cupones.cupones.utilities.exceptions.BussinessException;
import com.co.cupones.cupones.repository.FavoritosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CuponesService implements ICuponesService {

    @Autowired
    IPricesClient httpClient;


    /**
     * Metodo que obtiene los productos que cumplan con la regla de negocio
     *
     * @param cuponesInDto dto de entrada con el monto y los items
     * @return cuponesOutDto deto de salida con el total y los items seleccionados
     * @throws BussinessException excepcion de negocio
     */
    @Override
    public CuponesOutDto obtenerProductos(CuponesInDto cuponesInDto) throws BussinessException {
        FavoritosRepository.addFavorite(cuponesInDto.getItem_ids());
        List<PriceDto> items = this.getPrices(cuponesInDto.getItem_ids());
        return this.validatePriceAmount(items, cuponesInDto.getAmount());
    }


    /**
     * Metodo que obtiene el precio de los productos recibidos
     *
     * @param items items a consultar productos
     * @return listado de items y precios asociados
     */

    private List<PriceDto> getPrices(List<String> items) {
        String itemsString = String.join(",", items);
        List<ResponseApiDto> prices = httpClient.getPrices(itemsString);
        return prices.stream().map(ResponseApiDto::getBody).collect(Collectors.toList());
    }

    /**
     * Metodo que contiene las validaciones necesarias para cumplir el proceso eliminando los productos que superen
     * el costo del bono
     *
     * @param listPrices lista de productos con precios
     * @param amount     monto del bono
     * @return productos a comprar con el bono
     * @throws BussinessException Excepcion de negocio
     */
    private CuponesOutDto validatePriceAmount(List<PriceDto> listPrices, int amount) throws BussinessException {
        List<PriceDto> listaDepurada = listPrices.stream().filter(x -> x.getPrice() <= amount).collect(Collectors.toList());
        if (listaDepurada.isEmpty())
            throw new BussinessException(Constantes.NOT_FOUND_ITEMS);

        return this.validatePriceItems(listPrices, amount);

    }

    /**
     * Metodo que contiene las validaciones necesarias para cumplir el proceso verificando
     * que el total sea mayor al bono
     *
     * @param listPrices lista de productos con precios
     * @param amount     monto del bono
     */
    private CuponesOutDto validatePriceItems(List<PriceDto> listPrices, int amount) throws BussinessException {
        float total = (float) listPrices.stream().mapToDouble(PriceDto::getPrice).sum();
        if (total < amount) {
            CuponesOutDto cupones = new CuponesOutDto();
            cupones.setTotal(total);
            cupones.setItem_ids(listPrices.stream().map(PriceDto::getId).collect(Collectors.toList()));
            return cupones;
        } else {
            return this.calculateItemsForAmount(listPrices, amount);

        }

    }

    static void calcularItems_recursive(List<PriceDto> items, int amount, ArrayList<PriceDto> itemsPartial, List<CuponesOutDto> listaItems) {
        int s = 0;
        for (PriceDto x : itemsPartial) s += x.getPrice();
        if (s <= amount) {
            CuponesOutDto cupones = new CuponesOutDto();
            cupones.setTotal((float) itemsPartial.stream().mapToDouble(PriceDto::getPrice).sum());
            cupones.setItem_ids(itemsPartial.stream().map(PriceDto::getId).collect(Collectors.toList()));
            listaItems.add(cupones);
        }
        for (int i = 0; i < items.size(); i++) {
            ArrayList<PriceDto> remaining = new ArrayList<>();
            PriceDto n = items.get(i);
            for (int j = i + 1; j < items.size(); j++) remaining.add(items.get(j));
            ArrayList<PriceDto> itemsPartial_rec = new ArrayList<>(itemsPartial);
            itemsPartial_rec.add(n);
            calcularItems_recursive(remaining, amount, itemsPartial_rec, listaItems);
        }
    }

    private CuponesOutDto calculateItemsForAmount(List<PriceDto> listPrices, int amount) throws BussinessException {
        List<CuponesOutDto> listaItems = new ArrayList<>();
        calcularItems_recursive(listPrices, amount, new ArrayList<>(), listaItems);
        Optional<CuponesOutDto> itemSelected = listaItems.stream().max(Comparator.comparing(CuponesOutDto::getTotal));
        if (itemSelected.isPresent()) {
            return itemSelected.get();
        } else {
            throw new BussinessException(Constantes.ERROR_GET_ITEMS_SELECT);
        }
    }


}

