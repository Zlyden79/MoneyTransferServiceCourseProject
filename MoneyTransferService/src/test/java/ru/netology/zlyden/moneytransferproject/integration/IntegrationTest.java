package ru.netology.zlyden.moneytransferproject.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.zlyden.moneytransferproject.models.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    private static final GenericContainer<?> backendContainer =
            new GenericContainer<>("backend").withExposedPorts(8080);

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        backendContainer.start();
    }
    @AfterAll
    public static void shutDown() {
        backendContainer.stop();
    }

    @Test
    public void testTransferBadResponse(){
        //arrange
        var backendAppPort = backendContainer.getMappedPort(8080);
        String endpoint= "http://localhost:" + backendAppPort +"/transfer";
        //кривой запрос, валидатор не пропустит, контроллер пришлёт BadResponse
        MoneyTransfer moneyTransfer = new MoneyTransfer(
                "1111222233334444",
                "9999555544446666",
                "6g6",
                "06/29",
                new Amount("RUR", 1256900));
        BadResponse expected = new BadResponse("Invalid cardFromCVV: 6g6", 2);
        //act
        ResponseEntity<BadResponse> response = restTemplate.postForEntity(endpoint, moneyTransfer, BadResponse.class);
        //assert
        Assertions.assertEquals(expected, response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testTransferGoodResponse(){
        //arrange
        var backendAppPort = backendContainer.getMappedPort(8080);
        String endpoint= "http://localhost:" + backendAppPort +"/transfer";
        //правильный запрос, пройдёт все проверки валидаторами
        MoneyTransfer moneyTransfer = new MoneyTransfer(
                "1111222233334444",
                "9999555544446666",
                "686",
                "06/29",
                new Amount("RUR", 1256900));
        //При нормальных входных данных можно ожидать 1:5 статусы  HttpStatus.INTERNAL_SERVER_ERROR и HttpStatus.OK
        List<HttpStatus> statusList = List.of(HttpStatus.OK, HttpStatus.INTERNAL_SERVER_ERROR);

        //act
        ResponseEntity<GoodResponse> response = restTemplate.postForEntity(endpoint, moneyTransfer, GoodResponse.class);
        boolean isStatusInList = statusList.contains(response.getStatusCode());
        //assert
        Assertions.assertTrue(isStatusInList);
    }

    @Test
    public void testConfirmationBadResponse(){
        //arrange
        var backendAppPort = backendContainer.getMappedPort(8080);
        String endpoint= "http://localhost:" + backendAppPort +"/confirmOperation";
        //кривой запрос, валидатор не пропустит, контроллер пришлёт BadResponse
        ConfirmOperation confirmOperation = new ConfirmOperation("   ", "64589");
        BadResponse expected = new BadResponse("Invalid operationId:    ", 2);

        //act
        ResponseEntity<BadResponse> response = restTemplate.postForEntity(endpoint, confirmOperation, BadResponse.class);
        //assert
        Assertions.assertEquals(expected, response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testConfirmationGoodResponse(){
        //arrange
        var backendAppPort = backendContainer.getMappedPort(8080);
        String endpoint= "http://localhost:" + backendAppPort +"/confirmOperation";
        //правильный запрос, пройдёт все проверки валидаторами
        ConfirmOperation confirmOperation = new ConfirmOperation("93aa94ff-c659-45e3-9f11-6cb1a4f78ca7", "6459");
        //При нормальных входных данных можно ожидать 1:5 статусы  HttpStatus.INTERNAL_SERVER_ERROR и HttpStatus.OK
        List<HttpStatus> statusList = List.of(HttpStatus.OK, HttpStatus.INTERNAL_SERVER_ERROR);

        //act
        ResponseEntity<GoodResponse> response = restTemplate.postForEntity(endpoint, confirmOperation, GoodResponse.class);
        boolean isStatusInList = statusList.contains(response.getStatusCode());
        //assert
        Assertions.assertTrue(isStatusInList);
    }
}
