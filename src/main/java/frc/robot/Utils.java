package frc.robot;

/**
 * A utility class for methods that are used in multiple places
 *
 * Use {@code import static frc.robot.Utils.*} to import methods
 */
public final class Utils {
    private Utils() {}

    public static double squareKeepSign(double num) {
        if (num < 0)
            return -Math.pow(num, 2);
        else
            return Math.pow(num, 2);
    }
}
