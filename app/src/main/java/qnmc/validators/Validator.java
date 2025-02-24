package qnmc.validators;

public interface Validator {
    boolean validate(int number);
    int getBits();

    default int getMaxValue() {
        return (1 << getBits()) - 1;
    }

    default String getErrorMessage() {
        return "Number should be within 0 to " + getMaxValue() + "\nPlease press Next and give your input again";
    }

    // Static factory method
    static Validator of(int bits) {
        switch (bits) {
            case 3:
                return new ThreeBitsValidator();
            case 4:
                return new FourBitsValidator();
            case 5:
                return new FiveBitsValidator();
            default:
                throw new IllegalArgumentException("Unsupported number of bits: " + bits + ". Must be between 3 and 5");
        }
    }
}
