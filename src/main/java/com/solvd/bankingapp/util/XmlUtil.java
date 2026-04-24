package com.solvd.bankingapp.util;

import com.solvd.bankingapp.models.Account;
import com.solvd.bankingapp.models.Customer;
import jakarta.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XmlUtil {

    private static final Logger LOGGER = LogManager.getLogger(XmlUtil.class);

    public static Customer parseFromXml(String filePath) {
        Customer customer = new Customer();
        List<Account> accounts = new ArrayList<>();
        Account currentAccount = null;
        String currentElement = "";

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filePath));

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement = reader.getLocalName();
                        if (currentElement.equals("account")) {
                            currentAccount = new Account();
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (text.isEmpty()) break;

                        if (currentAccount != null) {
                            switch (currentElement) {
                                case "accountId":
                                    currentAccount.setAccountId(Long.parseLong(text));
                                    break;
                                case "accountNumber":
                                    currentAccount.setAccountNumber(text);
                                    break;
                                case "accountType":
                                    currentAccount.setAccountType(text);
                                    break;
                                case "balance":
                                    currentAccount.setBalance(new BigDecimal(text));
                                    break;
                                case "status":
                                    currentAccount.setStatus(text);
                                    break;
                            }
                        } else {
                            switch (currentElement) {
                                case "customerId":
                                    customer.setCustomerId(Long.parseLong(text));
                                    break;
                                case "fullName":
                                    customer.setFullName(text);
                                    break;
                                case "email":
                                    customer.setEmail(text);
                                    break;
                                case "phone":
                                    customer.setPhone(text);
                                    break;
                                case "nationalId":
                                    customer.setNationalId(text);
                                    break;
                                case "dateOfBirth":
                                    customer.setDateOfBirth(LocalDate.parse(text));
                                    break;
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if (reader.getLocalName().equals("account") && currentAccount != null) {
                            accounts.add(currentAccount);
                            currentAccount = null;
                        }
                        break;
                }
            }
            reader.close();
            customer.setAccounts(accounts);
            LOGGER.info("Successfully parsed customer from XML: {}", customer.getFullName());

        } catch (XMLStreamException | FileNotFoundException e) {
            LOGGER.error("Error parsing XML file", e);
        }

        return customer;
    }

    public static void marshal(Customer customer, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Customer.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(customer, new File(filePath));
            LOGGER.info("Successfully marshalled customer to XML: {}", filePath);
        } catch (JAXBException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            LOGGER.error("Full error: {}", sw.toString());
        }
    }

    public static Customer unmarshal(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Customer.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Customer customer = (Customer) unmarshaller.unmarshal(new File(filePath));
            LOGGER.info("Successfully unmarshalled customer from XML: {}", filePath);
            return customer;
        } catch (JAXBException e) {
            LOGGER.error("Error unmarshalling customer from XML", e);
        }
        return null;
    }
}