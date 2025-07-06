package Validators;

import java.util.Arrays;

public class FormatValidator extends StringNotNullNorEmptyValidator {

    private final String[] supportedFormats = { "pdf", "txt", "docx" };

    @Override
    public boolean check(String value) {
        return super.check(value) && Arrays.stream(supportedFormats).toList().contains(value.toLowerCase());
    }
}
