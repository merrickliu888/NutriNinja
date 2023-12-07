package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import java.awt.Component;
import java.io.IOException;
import javax.swing.JOptionPane;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupDataAccessInterface;
import view.SignupView;

public class SignupViewFactory {
    private SignupViewFactory() {
    }

    /**
     * Creates a SignupView
     * @param viewManagerModel
     * @param loginViewModel
     * @param signupViewModel
     * @param userDataAccessObject
     * @return
     */
    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupDataAccessInterface userDataAccessObject) {
        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel, viewManagerModel, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((Component)null, "Could not open user data file.");
            return null;
        }
    }

    /**
     * Creates a SignupUseCase
     * @param viewManagerModel
     * @param signupViewModel
     * @param loginViewModel
     * @param userDataAccessObject
     * @return a SignupController
     * @throws IOException
     */
    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupDataAccessInterface userDataAccessObject) throws IOException {
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        UserFactory userFactory = new CommonUserFactory();
        SignupInputBoundary userSignupInteractor = new SignupInteractor(userDataAccessObject, signupOutputBoundary, userFactory);
        return new SignupController(userSignupInteractor);
    }

}
