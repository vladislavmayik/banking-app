package com.solvd;

import com.solvd.bankingapp.dao.impl.AccountDAO;
import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.patterns.listener.LoggingTransactionListener;
import com.solvd.bankingapp.patterns.listener.NotificationTransactionListener;
import com.solvd.bankingapp.patterns.listener.TransactionEventPublisher;
import com.solvd.bankingapp.models.Customer;
import com.solvd.bankingapp.models.Transaction;
import com.solvd.bankingapp.patterns.mvc.AccountController;
import com.solvd.bankingapp.patterns.mvc.AccountView;
import com.solvd.bankingapp.patterns.strategy.CompoundInterestStrategy;
import com.solvd.bankingapp.patterns.strategy.LoanInterestCalculator;
import com.solvd.bankingapp.patterns.strategy.SimpleInterestStrategy;
import com.solvd.bankingapp.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

//        Customer customer = new Customer.Builder()
//                .id(1L)
//                .fullName("John Doe")
//                .email("john@email.com")
//                .phone("+1234567890")
//                .nationalId("ABC123456")
//                .dateOfBirth(LocalDate.of(1990, 5, 15))
//                .build();

        Customer customer1 = JsonUtil.readFromJson("src/main/resources/customers.json");
        LOGGER.info("Customer: {}", customer1.getFullName());
        customer1.getAccounts().forEach(account ->
                LOGGER.info("Account: {} | {} | {}", account.getAccountNumber(), account.getAccountType(), account.getBalance())
        );

        JsonUtil.writeToJson(customer1, "src/main/resources/customers_output.json");

//        TransactionEventPublisher publisher = new TransactionEventPublisher();
//        publisher.addListener(new LoggingTransactionListener());
//        publisher.addListener(new NotificationTransactionListener());
//
//        Transaction transaction = new Transaction();
//        transaction.setTransactionId(1L);
//        transaction.setAmount(new BigDecimal("500.00"));
//        transaction.setType("DEPOSIT");
//        transaction.setStatus("COMPLETED");
//
//        publisher.notifyListeners(transaction);
//
//        LoanInterestCalculator calculator = new LoanInterestCalculator(new SimpleInterestStrategy());
//        BigDecimal interest = calculator.calculate(BigDecimal.valueOf(10000), 0.05, 3);
//        LOGGER.info("Simple interest: {}", interest); // 1500.00
//
//        calculator.setStrategy(new CompoundInterestStrategy());
//        interest = calculator.calculate(BigDecimal.valueOf(10000), 0.05, 3);
//        LOGGER.info("Compound interest: {}", interest); // 1576.25
//
//        IAccountDAO accountDAO = new AccountDAO();
//        AccountView view = new AccountView();
//        AccountController controller = new AccountController(accountDAO, view);
//
//        controller.getAccount(1L);

//        Customer customer = XmlUtil.parseFromXml("src/main/resources/customers.xml");
//        LOGGER.info("Customer: {}", customer.getFullName());
//        LOGGER.info("Email: {}", customer.getEmail());
//        LOGGER.info("Accounts:");
//        customer.getAccounts().forEach(account ->
//                LOGGER.info("  - {} | {} | {}", account.getAccountNumber(), account.getAccountType(), account.getBalance())
//        );

//        Customer customer = XmlUtil.unmarshal("src/main/resources/customers.xml");
//        LOGGER.info("Customer: {}", customer.getFullName());
//        LOGGER.info("Number of accounts: {}", customer.getAccounts().size());
//        customer.getAccounts().forEach(account ->
//                LOGGER.info("Account: {} | {} | {}", account.getAccountNumber(), account.getAccountType(), account.getBalance())
//        );

//        XmlUtil.marshal(customer, "src/main/resources/customers_output.xml");
    }
}