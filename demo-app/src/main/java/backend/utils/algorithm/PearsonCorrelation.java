package backend.utils.algorithm;

import java.util.List;

public class PearsonCorrelation {
    public static double calculatePearsonCorrelation(List<Double> variableX, List<Double> variableY) {
        int n = variableX.size();
        double meanX = calculateMean(variableX);
        double meanY = calculateMean(variableY);

        double numerator = 0.0;
        double denominatorX = 0.0;
        double denominatorY = 0.0;

        for (int i = 0; i < n; i++) {
            double diffX = variableX.get(i) - meanX;
            double diffY = variableY.get(i) - meanY;

            numerator += diffX * diffY;
            denominatorX += diffX * diffX;
            denominatorY += diffY * diffY;
        }

        return numerator / Math.sqrt(denominatorX * denominatorY);
    }

    public static double calculateMean(List<Double> values) {
        double sum = 0.0;
        for (Double value : values) {
            sum += value;
        }
        return sum / values.size();
    }
}
