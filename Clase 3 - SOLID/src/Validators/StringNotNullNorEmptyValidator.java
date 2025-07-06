package Validators;

public class StringNotNullNorEmptyValidator implements Validator<String> {
    @Override
    public boolean check(String value) {
        return value != null && !value.isEmpty();
    }
}
