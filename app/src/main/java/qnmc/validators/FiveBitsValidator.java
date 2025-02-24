package qnmc.validators;

public class FiveBitsValidator implements Validator {

    private final int bits = 5;

    @Override
    public boolean validate(int number) {
        return number >= 0 && number <= getMaxValue();
    }

    @Override
    public int getBits() {
        return bits;
    }
}