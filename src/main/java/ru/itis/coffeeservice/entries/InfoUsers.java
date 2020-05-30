package ru.itis.coffeeservice.entries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoUsers {
    private String name;
    private String uniqueUrlName;
    private String status;
    private String from;
}
