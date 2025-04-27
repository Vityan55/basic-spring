package com.github.vityan55.spring.proxy;

import com.github.vityan55.spring.proxy.bean.Customer;
import com.github.vityan55.spring.proxy.bean.IWaiter;
import com.github.vityan55.spring.proxy.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("sample.jpg");
        image.display();
        image.display();

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        IWaiter john = context.getBean("john", IWaiter.class);
        Customer andrew = context.getBean("andrew", Customer.class);
        Customer nina = context.getBean("nina", Customer.class);
        Customer julia = context.getBean("julia", Customer.class);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> andrew.placeOrder(john)).start();
            new Thread(() -> nina.placeOrder(john)).start();
            new Thread(() -> julia.placeOrder(john)).start();
        }
    }


    // реализация паттерна прокси
    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private final String fileName;

        public RealImage(String fileName) {
            this.fileName = fileName;
            loadFromDisk();
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + fileName);
        }

        private void loadFromDisk(){
            System.out.println("Loading image: " + fileName);
        }
    }

    // проверяет, была ли загружена эта картинка и если была - просто вызывает метод display(), используя старый объект
    // RealImage в котором картинка была загружена
    static class ProxyImage implements Image {

        private RealImage realImage;

        private final String filename;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        @Override
        public void display() {
            if (realImage == null){
                realImage = new RealImage(filename);
            }

            realImage.display();
        }
    }
}
