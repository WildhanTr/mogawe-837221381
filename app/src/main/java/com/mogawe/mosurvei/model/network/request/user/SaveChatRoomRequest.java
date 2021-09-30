package com.mogawe.mosurvei.model.network.request.user;

public class SaveChatRoomRequest {
    private String uuidJob;
    private Long idChatRoom;

    public String getUuidJob() {
        return uuidJob;
    }

    public void setUuidJob(String uuidJob) {
        this.uuidJob = uuidJob;
    }

    public Long getIdChatRoom() {
        return idChatRoom;
    }

    public void setIdChatRoom(Long idChatRoom) {
        this.idChatRoom = idChatRoom;
    }
}
