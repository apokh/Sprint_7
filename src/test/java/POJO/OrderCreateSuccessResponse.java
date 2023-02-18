package POJO;

public class OrderCreateSuccessResponse {
    private String track;

    public OrderCreateSuccessResponse(String track) {
        this.track = track;
    }

    public OrderCreateSuccessResponse(){}

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }
}
