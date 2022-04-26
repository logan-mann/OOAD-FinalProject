package com.naiflogan.finalproject.backend.finalprojectbackend.request;

/**
 * Request class containing fields needed for request to /add_canvas in backend service
 */
public class AddCanvasRequest {
    private String canvasName;
    private boolean isPublic;
    private String jwt;

    public AddCanvasRequest(String canvasName, String jwt, boolean isPublic) {
        this.canvasName = canvasName;
        this.jwt = jwt;
        this.isPublic = isPublic;
    }


    public String getCanvasName() {
        return this.canvasName;
    }

    public String getJwt() {
        return this.jwt;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    
}
