package pojo.response;

import pojo.request.OrderBase;

public class OrderExtendedInfoByList extends OrderBase {
    private Long id;
    private Long courierId;
    private String track;
    private String createdAt;
    private String updatedAt;
    private Integer status;

    public OrderExtendedInfoByList(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color, Long id, Long courierId, String track, String createdAt, String updatedAt, Integer status) {
        super(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        this.id = id;
        this.courierId = courierId;
        this.track = track;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
