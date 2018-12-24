package com.revolut;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
* Revolut Transfer
* @date 14.12.2018
* @author Kanat K.B.
*/

@SpringBootApplication
@RestController
public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        try {
          SpringApplication.run(Application.class, args);
        } catch (Exception ex) {
            LOGGER.error("Error start application!", ex.getMessage());
        }
    }

    @RequestMapping("/start")
    public String home() {
        return "Start RevolutTransfer: "+ LocalDate.now();
    }
}
