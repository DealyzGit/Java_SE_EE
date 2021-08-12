package com.psbc.www;

import java.util.*;

public class Student {
    private String name;
    private Address adress;
    private String[] books;
    private List<String> hobbys;
    private Map<String, String> card;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public List<String> getHobbys() {
        return hobbys;
    }

    public void setHobbys(List<String> hobbys) {
        this.hobbys = hobbys;
    }

    public Map<String, String> getCard() {
        return card;
    }

    public void setCard(Map<String, String> card) {
        this.card = card;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public Properties getProperties() {
        return info;
    }

    public void setProperties(Properties properties) {
        this.info = properties;
    }

    private Set<String> games;
    private Properties info;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", adress=" + adress.toString() +
                ", books=" + Arrays.toString(books) +
                ", hobbys=" + hobbys +
                ", card=" + card +
                ", games=" + games +
                ", info=" + info +
                '}';
    }
}
