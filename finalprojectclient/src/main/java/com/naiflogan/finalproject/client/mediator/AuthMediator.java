package com.naiflogan.finalproject.client.mediator;


import com.naiflogan.finalproject.client.view.AuthView;

public class AuthMediator {

    private AuthView authView;

    public AuthMediator(AuthView authView) {
        this.authView = authView;
    }

    public void showLogin() {
        authView.showLoginView();
    }

    public void showCreateAccountView() {
        authView.showCreateAccountView();
    }

    public void showSelectionView() {
        authView.showSelectionView();
    }

    public void back() {
        authView.showPreviousView();
    }



    
}
