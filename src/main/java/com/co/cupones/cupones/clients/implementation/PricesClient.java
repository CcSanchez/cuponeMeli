package com.co.cupones.cupones.clients.implementation;

import com.co.cupones.cupones.clients.IPricesClient;
import com.co.cupones.cupones.models.ResponseApiDto;
import com.co.cupones.cupones.utilities.Constantes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PricesClient implements IPricesClient {
    /**
     * Metodo que realiza el llamado al api para obtener los precios de los productos
     * favoritos
     *
     * @param items productos a buscar precio
     * @return lista de precios de los productos
     * @throws RestClientException excepcion en la peticion
     */
    @Override
    public List<ResponseApiDto> getPrices(String items) throws RestClientException {
        URI url = UriComponentsBuilder.fromUriString(Constantes.PATH_PRICE)
                .pathSegment("items")
                .queryParam("ids", items).build().toUri();
        return Arrays.asList(Objects.requireNonNull(new RestTemplate().getForObject(url, ResponseApiDto[].class)));
    }

}
