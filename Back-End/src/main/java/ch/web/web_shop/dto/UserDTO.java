package ch.web.web_shop.dto;

public class UserDTO {
    private String name;
    private String email;
    private boolean subscribed;
    private String password;

    public UserDTO() {

    }

    public UserDTO(String name, String email, boolean subscribed, String password) {
        this.name = name;
        this.email = email;
        this.subscribed = subscribed;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", subscribed=" + subscribed +
                ", password='" + password + '\'' +
                '}';
    }
}
