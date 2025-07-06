package Validators;

public interface Validator<T> {
    boolean check(T value);
}
