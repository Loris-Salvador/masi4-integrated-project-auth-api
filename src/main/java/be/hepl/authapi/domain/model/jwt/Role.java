package be.hepl.authapi.domain.model.jwt;

public enum Role {
    CLIENT("client"),
    DELIVERY("delivery");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
