package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupOutputData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";
    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;
    private final JButton signUp;
    private final JButton alreadyHaveAccount = new JButton("Already have an account");

    private final ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public SignupView(SignupController controller, final SignupViewModel signupViewModel, ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        signupViewModel.addPropertyChangeListener(this);
        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel("Username"), this.usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(new JLabel("Password"), this.passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(new JLabel("Re-Enter Password"), this.repeatPasswordInputField);
        JPanel buttons = new JPanel();
        this.signUp = new JButton("Sign up");
        buttons.add(this.signUp);
        buttons.add(this.alreadyHaveAccount);
        this.alreadyHaveAccount.addActionListener(this);
        this.signUp.addActionListener((evt) -> {
            if (evt.getSource().equals(this.signUp)) {
                SignupState currentState = signupViewModel.getState();
                this.signupController.execute(currentState.getUsername(), currentState.getPassword(), currentState.getRepeatPassword());
            }

        });

        this.usernameInputField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                String user = SignupView.this.usernameInputField.getText();
                String text = user + e.getKeyChar();
                currentState.setUsername(text);
                signupViewModel.setState(currentState);
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        });
        this.passwordInputField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                String pass = SignupView.this.passwordInputField.getText();
                currentState.setPassword(pass + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        });
        this.repeatPasswordInputField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                String pass = SignupView.this.repeatPasswordInputField.getText();
                currentState.setRepeatPassword(pass + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, 1));
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt, SignupOutputData response) {

        if (evt.getSource() == this.signUp) {
            // Sign up logic
            SignupState currentState = signupViewModel.getState();
            this.signupController.execute(currentState.getUsername(), currentState.getPassword(), currentState.getRepeatPassword());
        } else if (evt.getSource() == this.alreadyHaveAccount) {
            // Logic to switch to the login view
            LoginState loginState = this.loginViewModel.getState();
            loginState.setUsername(response.getUsername());
            this.loginViewModel.setState(loginState);
            this.loginViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(this.loginViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState)evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
