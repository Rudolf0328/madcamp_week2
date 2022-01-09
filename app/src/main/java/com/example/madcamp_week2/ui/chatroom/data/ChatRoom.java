package com.example.madcamp_week2.ui.chatroom.data;

import java.util.ArrayList;

public class ChatRoom {
    private String name;
    private int img, maxUser, present;
    private ArrayList<String> people;

    public ChatRoom() {}

    public ChatRoom(int img, String name, int maxUser, int present, ArrayList<String> people) {
        this.img = img;
        this.name = name;
        this.maxUser = maxUser;
        this.present = present;
        this.people = people;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(int total) {
        this.maxUser = total;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public void setPeople(ArrayList<String> people) { this.people = people; }

    public ArrayList<String> getPeople() { return people; }

    public void toStringChatRoom() {
        System.out.println("img: " + img + ", name: " + name + ", total: " + maxUser + ", present: " + present + ", people" + people);
    }
}
