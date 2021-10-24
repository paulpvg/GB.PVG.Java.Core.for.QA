package com.company.hw04;

import java.util.*;

public class Phonebook {

    private Map<String, Set<String>> catalog = new HashMap<>();

    public void add(String name, String phone) {
        Set<String> phones = catalog.getOrDefault(name, new HashSet<>());
        phones.add(phone);
        catalog.put(name, phones);
    }

    public Set<String> get(String name) {
        return catalog.get(name);
    }
}
