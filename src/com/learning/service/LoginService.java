package com.learning.service;

import com.learning.common.EmailResponse;
import com.learning.model.EmailAccount;
import com.learning.view.EmailManager;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.*;
import java.nio.file.attribute.UserPrincipalNotFoundException;

public class LoginService extends Service<EmailResponse> {
    private EmailManager emailManager;
    private EmailAccount emailAccount;

    public LoginService(EmailManager emailManager, EmailAccount emailAccount) {
        this.emailManager = emailManager;
        this.emailAccount = emailAccount;
    }

    private EmailResponse login(){
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount.getAddress(), emailAccount.getPassword());
            }
        };
        Session session = Session.getInstance(emailAccount.getProperties(),authenticator);
        try {
            Store store = session.getStore("imaps");
            store.connect(emailAccount.getProperties().getProperty("incomingHost"),emailAccount.getAddress(),emailAccount.getPassword());
            emailAccount.setStore(store);
            emailManager.addEmailAccount(emailAccount);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return EmailResponse.FAILED_BY_NETWORK;
        } catch (AuthenticationFailedException e){   //Ordering of exception types is very crucial and should always be specific to generic.
            e.printStackTrace();
            return EmailResponse.FAILED_BY_CREDENTIALS;
        } catch (MessagingException e) {
            e.printStackTrace();
            return  EmailResponse.FAILED_BY_UNEXPECTED_ERROR;
        } catch (Exception e){
            e.printStackTrace();
            return EmailResponse.FAILED_BY_UNEXPECTED_ERROR;
        }
        return EmailResponse.SUCCESSFUL;
    }

    @Override
    protected Task<EmailResponse> createTask() {
        return new Task<EmailResponse>() {
            @Override
            protected EmailResponse call() throws Exception {
                return login();
            }
        };
    }
}
