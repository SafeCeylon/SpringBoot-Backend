package dev.safeceylon.SafeCeylon.DisasterVictim;

public enum DisasterType {
    landslide,
    flood,
    hurricane;

    public static DisasterType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("DisasterType cannot be null");
        }
        switch (value.toLowerCase()) {
            case "landslide":
                return landslide;
            case "flood":
                return flood;
            case "hurricane":
                return hurricane;
            default:
                throw new IllegalArgumentException("Unknown DisasterType: " + value);
        }
    }
}

