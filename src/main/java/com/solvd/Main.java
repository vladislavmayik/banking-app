package com.solvd;

import com.solvd.bankingapp.models.Customer;
import com.solvd.bankingapp.util.XmlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Customer customer = XmlUtil.parseFromXml("src/main/resources/customers.xml");
        LOGGER.info("Customer: {}", customer.getFullName());
        LOGGER.info("Email: {}", customer.getEmail());
        LOGGER.info("Accounts:");
        customer.getAccounts().forEach(account ->
                LOGGER.info("  - {} | {} | {}", account.getAccountNumber(), account.getAccountType(), account.getBalance())
        );

//        Customer customer = XmlUtil.unmarshal("src/main/resources/customers.xml");
//        LOGGER.info("Customer: {}", customer.getFullName());
//        LOGGER.info("Number of accounts: {}", customer.getAccounts().size());
//        customer.getAccounts().forEach(account ->
//                LOGGER.info("Account: {} | {} | {}", account.getAccountNumber(), account.getAccountType(), account.getBalance())
//        );

        XmlUtil.marshal(customer, "src/main/resources/customers_output.xml");
    }
}