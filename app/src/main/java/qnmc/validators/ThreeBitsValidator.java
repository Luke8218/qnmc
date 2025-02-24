package qnmc.validators;

public class ThreeBitsValidator implements Validator {

    private final int bits = 3;

    @Override
    public boolean validate(int number) {
        return number >= 0 && number <= getMaxValue();
    }

    @Override
    public int getBits() {
        return bits;
    }
}