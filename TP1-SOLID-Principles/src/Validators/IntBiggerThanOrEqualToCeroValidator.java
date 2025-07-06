package Validators;

public class IntBiggerThanOrEqualToCeroValidator implements Validator<Integer> {
    @Override
    public boolean check(Integer value) {
        return value >= 0;
    }
}
