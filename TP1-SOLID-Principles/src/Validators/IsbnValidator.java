package Validators;

import java.util.UUID;

public class IsbnValidator extends StringNotNullNorEmptyValidator {
    @Override
    public boolean check(String value) {
        return super.check(value) && isValidUuid(value);
    }

    private boolean isValidUuid(String value) {
        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
