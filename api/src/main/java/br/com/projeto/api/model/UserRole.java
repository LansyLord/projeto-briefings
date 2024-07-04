package br.com.projeto.api.model;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    public String getRole() {
        return role;
    }

    UserRole(String role){
        this.role = role;
    }



}
