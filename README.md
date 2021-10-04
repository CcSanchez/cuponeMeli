# Challegue Mercado Libre

Challegue tecnico mercado libre, con base a un listado de productos favoritos y un bono, retorna el listado de productos
que se ajusten al cupo del bono y adicional retorna los 5 productos mas favoritos.

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos
de desarrollo y pruebas._

### Instalación 🔧

```
mvn clean
```

```
mvn install
```

## End Points

* Peticion GET, obteniene los productos mas favoriteados
```
/coupon/stats
```

* Peticion POST, con base a una lista de productos y un monto, retorna los productos que al sumarlos obtengan un valor 
inferior y mas cercano al bono
```
/coupon
```


## Swagger

* [Local](http://localhost:8080/swagger-ui.html#/) - link a swagger al desplegarlo
  localmente(http://18.216.76.209:8080/swagger-ui.html#)


* [AWS](http://18.216.76.209:8080/swagger-ui.html#) - link a swagger en ASW (http://18.216.76.209:8080/swagger-ui.html#)

## Construido con 🛠️

* [Git](http://www.dropwizard.io/1.0.2/docs/) - Manejo de versiones
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [SpringBoot](https://rometools.github.io/rome/) - Framework usado
* [Jacoco](https://rometools.github.io/rome/) - Dependencia usada para evaluar el coverage de unit test

## Versionado 📌

Version 1.0.0 Creacion proyecto inicial que cumple con los requerimientos

## Autor ✒️

* **Cristian Camilo Sanchez Rozo** - *Challegue tecnico* - [csanchez](https://github.com/CcSanchez/cuponeMeli)

---
