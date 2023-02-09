# mini-crud

Sebuah simple project Spring Boot yang digenerate melalui [Spring generator](https://start.spring.io/).

## Kebutuhan

- [`Spring Initializr`](https://start.spring.io/)
- [`Free RemoteMySQL`](freemysqlhosting.net)
- [`JDK`](https://www.oracle.com/java/technologies/downloads/)


## Installation

1. Buka terminal / cmd
2. Clone repository menggunakan command ``git clone https://github.com/ariefw96/mini-crud.git``
3. Import project ke dalam intellij / eclipse
4. Tunggu hingga dependensi otomatis terdownload
5. Setup application properties, saya menggunakan mySQL (free)
```
YOUR_SQL_HOST=sql12.freemysqlhosting.net
YOUR_DATABASE=sql12596322
YOUR_SQL_USERNAME=sql12596322
YOUR_SQL_PASSWORD=VaXsNm5ZaE
```
6. setup JDK and root path 

![image](https://user-images.githubusercontent.com/70320451/217681934-b1d54a89-c5e2-4e29-aabd-d65d75b14f87.png)

7. add script spring-boot:run (script ini digunakan untun menjalankan aplikasi SpringBoot)

## Features

- CRUD Item
- Add/Remove Item to/from Cart
- Get Cart ( including finalize price)

## Notes

- Berjalan default pada port 8080, untuk mengubah tambahkan property ``server.port={YOUR_PORT}`` pada application.properties

## Documentation

- Swagger dapat diakses pada URL http://localhost:{PORT}/swagger-ui/#/

