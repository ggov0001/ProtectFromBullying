package com.example.protectfrombullying;


//POJO Class for Social media wise identities
public class BullyIdentitiesPlatform {

    private String platform;
    private String senderIdentity;
    private int numberOfMessagesSent;

    public BullyIdentitiesPlatform() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSenderIdentity() {
        return senderIdentity;
    }

    public void setSenderIdentity(String senderIdentity) {
        this.senderIdentity = senderIdentity;
    }

    public int getNumberOfMessagesSent() {
        return numberOfMessagesSent;
    }

    public void setNumberOfMessagesSent(int numberOfMessagesSent) {
        this.numberOfMessagesSent = numberOfMessagesSent;
    }
}
