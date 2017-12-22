package com.putatu.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Provider entity.
 */
public class ProviderDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String linkHome;

    private ZonedDateTime createAt;

    @Lob
    private byte[] image;
    private String imageContentType;

    private Float percent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkHome() {
        return linkHome;
    }

    public void setLinkHome(String linkHome) {
        this.linkHome = linkHome;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProviderDTO providerDTO = (ProviderDTO) o;
        if(providerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), providerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProviderDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", linkHome='" + getLinkHome() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", image='" + getImage() + "'" +
            ", percent=" + getPercent() +
            "}";
    }
}
