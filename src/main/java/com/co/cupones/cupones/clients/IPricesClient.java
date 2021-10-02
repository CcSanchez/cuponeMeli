package com.co.cupones.cupones.clients;

import com.co.cupones.cupones.models.ResponseApiDto;
import org.springframework.web.client.RestClientException;

import java.util.List;

public interface IPricesClient {
    /**
     * Metodo que realiza el llamado al api para obtener los precios de los productos
     * favoritos
     *
     * @param items productos a buscar precio
     * @return lista de precios de los productos
     * @throws RestClientException excepcion en la peticion
     */
    List<ResponseApiDto> getPrices(String items) throws RestClientException;
}
