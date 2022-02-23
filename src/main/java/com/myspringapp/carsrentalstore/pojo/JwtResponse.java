package com.myspringapp.carsrentalstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type="Bearer";
    private long id;
    private String userName;
    private List<String> roles;

    public JwtResponse(String token, long id, String userName, List<String> roles) {
        this.token = token;
        this.id = id;
        this.userName = userName;
        this.roles = roles;
    }
}
