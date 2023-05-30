import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinateGrouping {
    public static void main(String[] args) {
        double[][] coordinates = {{-168.15 , 140.58 },{-181.03 , -196.49 },{-60.73 , 115.23 },{-91.16 , -29.79 },{147.99 , 167.19 },{104.24 , -15.57 },{-137.65 , 104.71 },{12.49 , 123.87 },{142.48 , -7.36 },{24.15 , 108.21 },{-194.83 , 19.40 },{53.98 , 49.08 },{-73.78 , 61.43 },{-53.02 , 184.90 },{-113.63 , 97.67 },{29.15 , -170.92 },{-82.08 , 144.23 },{-172.39 , 59.01 },{85.57 , -40.41 },{-106.46 , -165.38 },{174.67 , 13.01 },{-68.44 , -141.00 },{-137.70 , 114.24 },{72.74 , 180.05 },{-189.57 , -130.08 },{-75.95 , -119.23 },{-51.97 , 162.22 },{-14.82 , -182.67 },{-2.04 , -62.06 },{144.32 , -162.20 },{56.69 , 151.23 },{-151.97 , -149.40 },{149.28 , -79.18 },{185.50 , -89.10 },{-41.03 , 42.49 },{-63.77 , 154.18 },{170.18 , -59.56 },{0.46 , 130.21 },{-134.20 , -54.11 },{-34.86 , 76.91 },{183.90 , -140.81 },{53.81 , -77.51 },{166.14 , -118.44 },{167.01 , 115.48 },{19.45 , -4.78 },{-59.53 , 156.24 },{-135.39 , -74.20 },{131.69 , -60.35 },{59.80 , -61.56 },{191.05 , 117.74 },{-35.92 , 99.08 },{-18.18 , -147.80 },{-54.74 , -124.96 },{84.40 , -49.42 },{138.64 , -145.98 },{152.79 , 66.34 },{127.36 , 128.33 },{-12.36 , -8.32 },{-57.71 , -78.96 },{147.87 , -116.52 },{168.62 , -76.32 },{43.92 , 136.48 },{-101.48 , 169.62 },{-61.25 , 154.09 },{183.37 , 85.55 },{-71.65 , 66.21 },{-83.72 , 42.90 },{181.88 , 104.53 },{-84.94 , 74.12 },{92.22 , 69.95 },{177.46 , -176.56 },{-79.25 , 112.63 },{45.38 , -130.37 },{-183.92 , -105.55 },{141.37 , -111.96 },{144.67 , 114.81 },{30.36 , 112.08 },{-108.29 , -45.70 },{-188.57 , 9.24 },{74.17 , -172.37 },{58.95 , 4.11 },{-146.51 , 135.46 },{-90.65 , 61.56 },{-56.60 , -133.49 },{29.21 , 184.93 },{179.49 , -75.08 },{102.26 , -9.91 },{149.86 , 144.40 },{137.60 , -116.94 },{-17.63 , 99.16 },{127.93 , 199.35 },{-58.28 , 33.39 },{176.56 , 155.75 },{10.73 , -95.53 } };
        double threshold = 80.0; // Yakınlık eşiği

        Map<Integer, List<double[]>> groups = groupCoordinates(coordinates, threshold);

        for (List<double[]> group : groups.values()) {
            System.out.println("Grup:");
            for (double[] coordinate : group) {
                System.out.println(coordinate[0] + ", " + coordinate[1]);
            }
            System.out.println();
        }
    }

    public static Map<Integer, List<double[]>> groupCoordinates(double[][] coordinates, double threshold) {
        Map<Integer, List<double[]>> groups = new HashMap<>();
        int groupId = 0;

        for (double[] coordinate : coordinates) {
            boolean foundGroup = false;
            for (List<double[]> group : groups.values()) {
                double[] reference = group.get(0);
                double distance = getDistance(coordinate, reference);
                if (distance <= threshold) {
                    group.add(coordinate);
                    foundGroup = true;
                    break;
                }
            }
            if (!foundGroup) {
                List<double[]> newGroup = new ArrayList<>();
                newGroup.add(coordinate);
                groups.put(groupId, newGroup);
                groupId++;
            }
        }

        return groups;
    }

    public static double getDistance(double[] point1, double[] point2) {
        double dx = point1[0] - point2[0];
        double dy = point1[1] - point2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}
