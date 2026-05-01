package com.solvd.bankingapp.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.bankingapp.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonUtil {

    private static final Logger LOGGER = LogManager.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public static Customer readFromJson(String filePath) {
        try {
            Customer customer = mapper.readValue(new File(filePath), Customer.class);
            LOGGER.info("Successfully parsed customer from JSON: {}", customer.getFullName());
            return customer;
        } catch (IOException e) {
            LOGGER.error("Error reading customer from JSON", e);
        }
        return null;
    }

    public static void writeToJson(Customer customer, String filePath) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), customer);
            LOGGER.info("Successfully wrote customer to JSON: {}", filePath);
        } catch (IOException e) {
            LOGGER.error("Error writing customer to JSON", e);
        }
    }
}