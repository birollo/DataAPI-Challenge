package com.swisscom.DataAPIChallenge.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Dialog {

    @Id
    private String dialogId;

    @Enumerated(EnumType.STRING)
    private Language language;

    private String text;

    private LocalDateTime dateTime;

    private boolean consent;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

    public Dialog() {
    }

    public Dialog(String dialogId,Language language, String text, LocalDateTime dateTime,boolean consent, Customer customer) {
        this.dialogId = dialogId;
        this.language = language;
        this.text = text;
        this.dateTime = dateTime;
        this.consent = consent;
        this.customer = customer;
    }

    public String getDialogId() {
        return dialogId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Dialog{" +
                "dialogId='" + dialogId + '\'' +
                ", customerId=" + customer.getCustomerId() +
                ", language='" + language + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime.format(formatter) +
                ", consent=" + consent +
                '}';
    }
}
