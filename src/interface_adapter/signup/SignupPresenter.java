package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import interface_adapter.login.LoginViewModel;

public class    SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Creates the success view (login view) and sets the username if the signup was successful
     * @param response
     */
    public void prepareSuccessView(SignupOutputData response) {
        LoginState loginState = this.loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        this.loginViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(this.loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Creates the fail view and sets the error message if the signup was unsuccessful
     * @param error
     */
    public void prepareFailView(String error) {
        SignupState signupState = this.signupViewModel.getState();
        signupState.setUsernameError(error);
        this.signupViewModel.firePropertyChanged();
    }
}

