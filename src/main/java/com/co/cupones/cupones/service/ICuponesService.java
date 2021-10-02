package com.co.cupones.cupones.service;

import com.co.cupones.cupones.models.CuponesInDto;
import com.co.cupones.cupones.models.CuponesOutDto;
import com.co.cupones.cupones.utilities.exceptions.BussinessException;

import java.net.URISyntaxException;

public interface ICuponesService {

    CuponesOutDto obtenerProductos(CuponesInDto cuponesInDto) throws URISyntaxException, BussinessException;

}
