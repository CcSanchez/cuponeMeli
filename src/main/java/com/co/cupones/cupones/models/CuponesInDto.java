package com.co.cupones.cupones.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CuponesInDto {

    @NotEmpty(message = "Es necesario enviar productos favoritos")
    @ApiModelProperty(value = "Lista de productos favoritos", required = true, example = "[\"MLA1\", \"MLA2\", \"MLA3\", \"MLA4\", \"MLA5\"]")
    List<String> item_ids;

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "Monto del bono", required = true, example = "500")
    Integer amount;

}
