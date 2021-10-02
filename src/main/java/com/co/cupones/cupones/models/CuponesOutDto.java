package com.co.cupones.cupones.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CuponesOutDto {

    List<String> item_ids;

    float total;
}
