package use_case.save_preferences;

import entity.UserPreferences;

public class SavePreferencesInteractor implements SavePreferencesInputBoundary {
        final SavePreferencesDataAccessInterface saveDataAccessObject;
        final SavePreferencesOutputBoundary savePresenter;


    public SavePreferencesInteractor(SavePreferencesDataAccessInterface saveDataAccessObject,
                                     SavePreferencesOutputBoundary savePresenter){
        this.saveDataAccessObject = saveDataAccessObject;
        this.savePresenter = savePresenter;
    }

    @Override
    public void execute(SavePreferencesInputData savePreferencesInputData) {
        UserPreferences userPreferences = new UserPreferences(savePreferencesInputData.getNutrientRange(),
                savePreferencesInputData.getHealthPreferences(),
                savePreferencesInputData.getDietPreference());
        saveDataAccessObject.saveUserPreferences(savePreferencesInputData.getUsername(), userPreferences);
        SavePreferencesOutputData savePreferencesOutputData = new SavePreferencesOutputData();
        savePresenter.prepareSuccessView(savePreferencesOutputData);
    }

}
