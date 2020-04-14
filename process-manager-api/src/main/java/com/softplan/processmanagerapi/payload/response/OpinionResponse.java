package com.softplan.processmanagerapi.payload.response;

public class OpinionResponse {
    private Long id;
    private UserResponse user;
    private String opinion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @Override
    public String toString() {
        return "OpinionResponse{" +
                "id=" + id +
                ", user=" + user.getName() +
                ", opinion='" + opinion + '\'' +
                '}';
    }
}
