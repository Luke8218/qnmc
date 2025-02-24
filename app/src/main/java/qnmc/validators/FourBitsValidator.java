package qnmc.validators;

public class FourBitsValidator implements Validator {

    private final int bits = 4;

    @Override
    public boolean validate(int number) {
        return number >= 0 && number <= getMaxValue();
    }

    @Override
    public int getBits() {
        return bits;
    }
}