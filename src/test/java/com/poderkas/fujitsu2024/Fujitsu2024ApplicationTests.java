package com.poderkas.fujitsu2024;

import com.poderkas.fujitsu2024.delivery.Delivery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Fujitsu2024ApplicationTests {

    @Test
    void addDelivery(){
        Delivery delivery = new Delivery(1712029400000L, "Tallinn", "Bike");

    }
}
