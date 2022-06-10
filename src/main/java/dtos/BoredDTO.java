package dtos;

import entities.Bored;

public class BoredDTO {

    private String activity;
    private String type;
    private int participants;
    private float price;
    private String link;
    private String key;
    private float accessibility;

    public BoredDTO() {
    }

    public BoredDTO(Bored bored) {
        this.activity = bored.getActivity();
        this.type = bored.getType();
        this.participants = bored.getParticipants();
        this.price = bored.getPrice();
        this.link = bored.getLink();
        this.key = bored.getKey();
        this.accessibility = bored.getAccessibility();
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public float getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(float accessibility) {
        this.accessibility = accessibility;
    }
}
