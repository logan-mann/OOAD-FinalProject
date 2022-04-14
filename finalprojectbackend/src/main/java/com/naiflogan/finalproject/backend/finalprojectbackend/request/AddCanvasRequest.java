package com.naiflogan.finalproject.backend.finalprojectbackend.request;

public class AddCanvasRequest {
    private String canvasName;
    private boolean isPublic;

    //TODO - replace with JWT
    private String username;

    public AddCanvasRequest(String canvasName, String username, boolean isPublic) {
        this.canvasName = canvasName;
        this.username = username;
        this.isPublic = isPublic;
    }


    public String getCanvasName() {
        return this.canvasName;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    
}
