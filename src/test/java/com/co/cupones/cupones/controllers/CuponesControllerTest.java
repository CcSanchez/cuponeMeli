package com.co.cupones.cupones.controllers;

import com.co.cupones.cupones.models.CuponesInDto;
import com.co.cupones.cupones.models.CuponesOutDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CuponesControllerTest {

    private static final String URL = "http://localhost:";

    private static final String END_POINT = "/coupon";

    private static final String END_POINT_GET = "/coupon/stats";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    void notParameters() {
        String url = URL + port + END_POINT;
        CuponesInDto cuponesInDto = new CuponesInDto();
        ResponseEntity<CuponesOutDto> cupones = this.restTemplate.postForEntity(url, cuponesInDto, CuponesOutDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, cupones.getStatusCode());
    }

    @Test
    void notItems() {
        String url = URL + port + END_POINT;
        CuponesInDto cuponesInDto = new CuponesInDto();
        cuponesInDto.setAmount(500);
        ResponseEntity<CuponesOutDto> cupones = this.restTemplate.postForEntity(url, cuponesInDto, CuponesOutDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, cupones.getStatusCode());
    }

    @Test
    void notAmount() {
        String url = URL + port + END_POINT;
        CuponesInDto cuponesInDto = new CuponesInDto();
        List<String> items_id = new ArrayList<>();
        items_id.add("MLA816019440");
        items_id.add("MLA811601010");
        items_id.add("MLA919368645");
        items_id.add("MLA932252932");
        cuponesInDto.setItem_ids(items_id);
        ResponseEntity<CuponesOutDto> cupones = this.restTemplate.postForEntity(url, cuponesInDto, CuponesOutDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, cupones.getStatusCode());
    }

    @Test
    void ceroAmount() {
        String url = URL + port + END_POINT;
        CuponesInDto cuponesInDto = new CuponesInDto();
        List<String> items_id = new ArrayList<>();
        items_id.add("MLA816019440");
        items_id.add("MLA811601010");
        items_id.add("MLA919368645");
        items_id.add("MLA932252932");
        cuponesInDto.setItem_ids(items_id);
        cuponesInDto.setAmount(0);
        ResponseEntity<CuponesOutDto> cupones = this.restTemplate.postForEntity(url, cuponesInDto, CuponesOutDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, cupones.getStatusCode());
    }

    @Test
    void minAmount() {
        String url = URL + port + END_POINT;
        CuponesInDto cuponesInDto = new CuponesInDto();
        List<String> items_id = new ArrayList<>();
        items_id.add("MLA816019440");
        items_id.add("MLA811601010");
        items_id.add("MLA919368645");
        items_id.add("MLA932252932");
        cuponesInDto.setItem_ids(items_id);
        cuponesInDto.setAmount(100);
        ResponseEntity<CuponesOutDto> cupones = this.restTemplate.postForEntity(url, cuponesInDto, CuponesOutDto.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, cupones.getStatusCode());
    }

    @Test
    void okCalculate() {
        String url = URL + port + END_POINT;
        CuponesInDto cuponesInDto = new CuponesInDto();
        List<String> items_id = new ArrayList<>();
        items_id.add("MLA816019440");
        items_id.add("MLA811601010");
        items_id.add("MLA919368645");
        items_id.add("MLA932252932");
        items_id.add("MLA886405818");
        items_id.add("MLA913010868");
        cuponesInDto.setItem_ids(items_id);
        cuponesInDto.setAmount(100000);
        ResponseEntity<CuponesOutDto> cupones = this.restTemplate.postForEntity(url, cuponesInDto, CuponesOutDto.class);
        assertEquals(HttpStatus.OK, cupones.getStatusCode());
    }

    @Test
    void okTotalMenorAmount() {
        String url = URL + port + END_POINT;
        CuponesInDto cuponesInDto = new CuponesInDto();
        List<String> items_id = new ArrayList<>();
        items_id.add("MLA816019440");
        items_id.add("MLA811601010");
        items_id.add("MLA919368645");
        items_id.add("MLA932252932");
        items_id.add("MLA886405818");
        items_id.add("MLA913010868");
        cuponesInDto.setItem_ids(items_id);
        cuponesInDto.setAmount(1000000);
        ResponseEntity<CuponesOutDto> cupones = this.restTemplate.postForEntity(url, cuponesInDto, CuponesOutDto.class);
        assertEquals(HttpStatus.OK, cupones.getStatusCode());
    }

}