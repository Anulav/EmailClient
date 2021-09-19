module EmailClient {
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires activation;
    requires java.mail;

    opens com.learning;
    opens com.learning.view;
    opens com.learning.controller;
}