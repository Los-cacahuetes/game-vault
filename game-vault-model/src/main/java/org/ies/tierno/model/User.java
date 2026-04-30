package org.ies.tierno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String email;
    private String balance;
    private Role role;
}
