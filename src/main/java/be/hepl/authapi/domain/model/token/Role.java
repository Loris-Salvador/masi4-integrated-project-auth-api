package be.hepl.authapi.domain.model.token;

/// <comments>
/// Rôle possible (mis dans le token)
/// </comments>
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
