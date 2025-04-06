package be.hepl.authapi.domain.model.token;

public enum Role {
    CUSTOMER("customer"),
    DRIVER("driver");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
