package by.shershen.flats_service.core.entity;

public enum OfferType {

    RENT("RENT"),
    SALE("SALE");

    private final String typeId;

    OfferType(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeId() {
        return typeId;
    }
}
