package com.swisscom.DataAPIChallenge.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dialog {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String DialogId;

    private String CustomerId;

    private String text;

    private String language;

    private boolean consent;

    public Dialog() {
    }

    public Dialog(String dialogId, String customerId, String text, String language, boolean consent) {
        DialogId = dialogId;
        CustomerId = customerId;
        this.text = text;
        this.language = language;
        this.consent = consent;
    }

    public String getDialogId() {
        return DialogId;
    }

    public void setDialogId(String dialogId) {
        DialogId = dialogId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }
}
