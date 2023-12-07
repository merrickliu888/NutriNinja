
package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {
    final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Creates InputData to pass to the Use Case Interactor
     * @param username
     * @param password
     * @param repeatPassword
     */
    public void execute(String username, String password, String repeatPassword) {
        SignupInputData signupInputData = new SignupInputData(username, password, repeatPassword);
        this.userSignupUseCaseInteractor.execute(signupInputData);
    }
}
