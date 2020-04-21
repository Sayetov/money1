package com.example.money1;

public class Messeige {
    private String Athor;
    private String textOfMessage;
    private String sum;
    private Long date;



    public String getAthor() {
        return Athor;
    }

    public void setAthor(String athor) {
        Athor = athor;
    }

    public String getTextOfMessage() {
        return textOfMessage;
    }

    public String getSum() {return sum;}

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setTextOfMessage(String textOfMessage) {
        this.textOfMessage = textOfMessage;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Messeige(String athor, String textOfMessage, String sum, Long date) {
        Athor = athor;
        this.textOfMessage = textOfMessage;
        this.sum = sum;
        this.date = date;
    }

    public Messeige() {
    }
}
