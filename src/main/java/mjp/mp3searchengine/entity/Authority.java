package mjp.mp3searchengine.entity;

public enum Authority {

    USER_MANAGEMENT("USER_MANAGEMENT"),

    AUDIO_MANAGEMENT("AUDIO_MANAGEMENT"),

    AUTHORITY_MANAGEMENT("AUTHORITY_MANAGEMENT");

    private String value;

    Authority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isSupported(String value) {
        for (Authority authority : Authority.values()) {
            if (authority.getValue().equals(value.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
