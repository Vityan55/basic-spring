package com.github.vityan55.spring.event.bean;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/*
Здесь мы получаем заказы, с помощью сервера отправляем событие orderEvent, которое успешно обрабатываем
Помещаем заказы в очередь, бин Chef достает из этой очереди заказы и может выполнять свою работу
 */

@Component
public class BasicHttpServer {
    private final Restaurant restaurant;

    public BasicHttpServer(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @PostConstruct
    public void startServer() {
        start();
    }

    private void start() {
        try {
            // создаем сервер который будет работать на порту 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // указываем на единственный энд поинт, задавая путь и обработчик
            server.createContext("/order", processOrderHandler());

            // запускаем сервер
            server.start();

            System.out.println("Server is running on 8080 port...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpHandler processOrderHandler() {
        // в exchange хранится тело, параметры запроса
        return exchange -> {
            // проверка на то, что наш запрос - пост запрос
            if ("POST".equals(exchange.getRequestMethod())){
                // получаем входной поток для чтения тела запроса
                InputStream requestBody = exchange.getRequestBody();

                // читаем тело запроса
                byte[] bodyBytes = requestBody.readAllBytes();
                // получаем заказ
                String order = new String(bodyBytes);

                System.out.println("Received order from request: " + order);

                // отправляем событие нового заказа
                restaurant.placeOrder(order);

                // отправляем ответ клиенту
                String response = "Order received successfully";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        };
    }
}