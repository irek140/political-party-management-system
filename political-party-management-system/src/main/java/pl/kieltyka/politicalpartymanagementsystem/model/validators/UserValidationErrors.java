package pl.kieltyka.politicalpartymanagementsystem.model.validators;

import java.util.HashMap;
import java.util.Map;

public class UserValidationErrors {

    private Map<String, String> errors;

    public UserValidationErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public UserValidationErrors() {
        this.errors = new HashMap<>();
    }

    public void addError(String key, String value) {
        this.errors.put(key, value);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

}
