package by.it_academy.jd2.flats_service.core.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "users", name = "flats")
public class FlatEntity {

    @Id
    private UUID uuid;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @Column(name = "offer_type")
    private OfferType offerType;

    private String description;

    private Integer bedrooms;

    private Integer area;

    private Integer price;

    private Integer floor;

    @Type(StringArrayType.class)
    @Column(name = "photo_urls", columnDefinition = "text[]")
    private String[] photoUrls;

    @Column(name = "original_url")
    private String originalUrl;

    public FlatEntity() {
    }

    public FlatEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, OfferType offerType, String description, Integer bedrooms, Integer area, Integer price, Integer floor, String[] photoUrls, String originalUrl) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.offerType = offerType;
        this.description = description;
        this.bedrooms = bedrooms;
        this.area = area;
        this.price = price;
        this.floor = floor;
        this.photoUrls = photoUrls;
        this.originalUrl = originalUrl;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatEntity that = (FlatEntity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate) && offerType == that.offerType && Objects.equals(description, that.description) && Objects.equals(bedrooms, that.bedrooms) && Objects.equals(area, that.area) && Objects.equals(price, that.price) && Objects.equals(floor, that.floor) && Arrays.equals(photoUrls, that.photoUrls) && Objects.equals(originalUrl, that.originalUrl);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(uuid, dtCreate, dtUpdate, offerType, description, bedrooms, area, price, floor, originalUrl);
        result = 31 * result + Arrays.hashCode(photoUrls);
        return result;
    }

    @Override
    public String toString() {
        return "FlatEntity{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", offerType=" + offerType +
                ", description='" + description + '\'' +
                ", bedrooms=" + bedrooms +
                ", area=" + area +
                ", price=" + price +
                ", floor=" + floor +
                ", photoUrls=" + Arrays.toString(photoUrls) +
                ", originalUrl='" + originalUrl + '\'' +
                '}';
    }
}
