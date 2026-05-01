package com.solvd.bankingapp.facade;

import com.solvd.bankingapp.dao.factory.DAOFactory;
import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.dao.interfaces.ICustomerDAO;
import com.solvd.bankingapp.dao.interfaces.ITransactionDAO;
import com.solvd.bankingapp.listener.LoggingTransactionListener;
import com.solvd.bankingapp.listener.NotificationTransactionListener;
import com.solvd.bankingapp.listener.TransactionEventPublisher;
import com.solvd.bankingapp.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankingFacade {

    private static final Logger LOGGER = LogManager.getLogger(BankingFacade.class);

    private final IAccountDAO accountDAO;
    private final ITransactionDAO transactionDAO;
    private final ICustomerDAO customerDAO;
    private final TransactionEventPublisher publisher;

    public BankingFacade() {
        DAOFactory factory = DAOFactory.getInstance(DAOFactory.MYBATIS);
        this.accountDAO     = factory.getAccountDAO();
        this.transactionDAO = factory.getTransactionDAO();
        this.customerDAO    = factory.getCustomerDAO();

        this.publisher = new TransactionEventPublisher();
        this.publisher.addListener(new LoggingTransactionListener());
        this.publisher.addListener(new NotificationTransactionListener());
    }

    public Account openAccount(Long customerId, String accountNumber, String accountType, BigDecimal initialBalance, Long currencyId) {
        LOGGER.info("Opening new account for customer {}", customerId);

        Account account = new Account();
        account.setCustomerId(customerId);
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(initialBalance);
        account.setCurrencyId(currencyId);
        account.setStatus("ACTIVE");
        accountDAO.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(account.getAccountId());
        transaction.setAmount(initialBalance);
        transaction.setType("DEPOSIT");
        transaction.setStatus("COMPLETED");
        transaction.setCreatedAt(LocalDateTime.now());
        transactionDAO.save(transaction);

        publisher.notifyListeners(transaction);

        LOGGER.info("Account {} opened successfully", accountNumber);
        return account;
    }

    public Customer registerCustomer(String fullName, String email, String phone, String nationalId) {
        LOGGER.info("Registering new customer: {}", fullName);

        Customer customer = new Customer.Builder()
                .fullName(fullName)
                .email(email)
                .phone(phone)
                .nationalId(nationalId)
                .build();
        customerDAO.save(customer);

        LOGGER.info("Customer {} registered successfully", fullName);
        return customer;
    }
}