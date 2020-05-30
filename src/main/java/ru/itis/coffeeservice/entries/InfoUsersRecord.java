package ru.itis.coffeeservice.entries;

import lombok.Data;

@Data
public class InfoUsersRecord {
    private String name;
    private String uniqueUrlName;
    private String status;
}
