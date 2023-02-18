package POJO;

public class OrderExtendedInfoByNumber extends OrderBase {
    private Long id;
    private String track;
    private Boolean cancelled;
    private Boolean finished;
    private Boolean inDelivery;
    private String createdAt;
    private String updatedAt;
    private Integer status;

    public OrderExtendedInfoByNumber(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color, Long id, String track, Boolean cancelled, Boolean finished, Boolean inDelivery, String createdAt, String updatedAt, Integer status) {
        super(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        this.id = id;
        this.track = track;
        this.cancelled = cancelled;
        this.finished = finished;
        this.inDelivery = inDelivery;
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

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean getInDelivery() {
        return inDelivery;
    }

    public void setInDelivery(Boolean inDelivery) {
        this.inDelivery = inDelivery;
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
