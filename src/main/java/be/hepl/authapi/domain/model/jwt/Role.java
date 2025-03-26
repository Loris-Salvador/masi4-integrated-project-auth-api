package be.hepl.authapi.domain.model.jwt;

/// <comments>
/// Rôle possible (mis dans le token)
/// </comments>
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
