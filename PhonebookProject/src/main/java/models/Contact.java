package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class Contact {
    private String Name;
    private String lastName;
    private int phone;
    private String email;
    private String address;
    private int description;
}
