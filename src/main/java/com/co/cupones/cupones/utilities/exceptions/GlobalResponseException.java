/*
 *  Copyright (c) 2016 - Concesion RUNT S.A, Todos los derechos reservados.
 *  Proyecto: utilidades-transversal-runt
 *  Servicios utilitarios
 */
package com.co.cupones.cupones.utilities.exceptions;

import java.util.stream.Collectors;

import com.co.cupones.cupones.models.Mensaje;
import com.co.cupones.cupones.models.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalResponseException {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalResponseException.class);

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Mensaje> handleExceptionBadRequest(MethodArgumentNotValidException ex) {
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigoResultado("FALLIDO");
        mensaje.setValidaciones(ex.getBindingResult().getAllErrors().stream().map(this::mapError)
                .collect(Collectors.toList()));
        return new ResponseEntity<Mensaje>(mensaje, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Mensaje> handleExceptionInternalServer(Exception ex) {
        LOGGER.error("INTERNAL_SERVER_ERROR:", ex);
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigoResultado("ERROR");
        mensaje.setDescripcionRespuesta(ex.getMessage());
        return new ResponseEntity<Mensaje>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ValidationError mapError(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            return new ValidationError(((FieldError) objectError).getField(),
                    objectError.getDefaultMessage());
        }
        return new ValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }


}
