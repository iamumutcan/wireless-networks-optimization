import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        double[] coordinatesX = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] coordinatesY = {1.0, 2.0, 3.0, 4.0, 5.0};

        int[] baseStationMaxDevice = {2, 3, 2}; // Örnek olarak 3 baz istasyonu için cihaz sayıları
        double[] baseStationMaxRange = {2.5, 3.0, 2.0}; // Örnek olarak 3 baz istasyonunun kapsama alanları

        List<List<Integer>> groupedDevices = groupDevices(coordinatesX, coordinatesY, baseStationMaxDevice, baseStationMaxRange);

        for (int i = 0; i < groupedDevices.size(); i++) {
            System.out.println("Baz İstasyonu " + (i + 1) + " için gruplandırılan cihazlar:");
            List<Integer> group = groupedDevices.get(i);
            for (int deviceId : group) {
                System.out.println("Cihaz ID: " + deviceId);
            }
            System.out.println();
        }

        System.out.println("Gruplandırılmamış cihazlar:");
        List<Integer> ungroupedDevices = getUngroupedDevices(groupedDevices, coordinatesX.length);
        for (int deviceId : ungroupedDevices) {
            System.out.println("Cihaz ID: " + deviceId);
        }
    }

    public static List<List<Integer>> groupDevices(double[] coordinatesX, double[] coordinatesY, int[] baseStationMaxDevice, double[] baseStationMaxRange) {
        List<List<Integer>> groupedDevices = new ArrayList<>();
        for (int i = 0; i < baseStationMaxDevice.length; i++) {
            groupedDevices.add(new ArrayList<>());
        }

        for (int deviceId = 0; deviceId < coordinatesX.length; deviceId++) {
            double deviceX = coordinatesX[deviceId];
            double deviceY = coordinatesY[deviceId];
            int assignedBaseStation = -1;
            double minDistance = Double.MAX_VALUE;

            for (int i = 0; i < baseStationMaxDevice.length; i++) {
                if (groupedDevices.get(i).size() < baseStationMaxDevice[i]) {
                    double baseStationX = calculateBaseStationX(coordinatesX, groupedDevices.get(i));
                    double baseStationY = calculateBaseStationY(coordinatesY, groupedDevices.get(i));
                    double distance = calculateDistance(deviceX, deviceY, baseStationX, baseStationY);

                    if (distance <= baseStationMaxRange[i] && distance < minDistance) {
                        assignedBaseStation = i;
                        minDistance = distance;
                    }
                }
            }

            if (assignedBaseStation != -1) {
                groupedDevices.get(assignedBaseStation).add(deviceId);
            }
        }

        return groupedDevices;
    }

    public static double calculateBaseStationX(double[] coordinatesX, List<Integer> group) {
        double sumX = 0;
        for (int deviceId : group) {
            sumX += coordinatesX[deviceId];
        }
        return sumX / group.size();
    }

    public static double calculateBaseStationY(double[] coordinatesY, List<Integer> group) {
        double sumY = 0;
        for (int deviceId : group) {
            sumY += coordinatesY[deviceId];
        }
        return sumY / group.size();
    }

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static List<Integer> getUngroupedDevices(List<List<Integer>> groupedDevices, int totalDevices) {
        List<Integer> ungroupedDevices = new ArrayList<>();
        for (int i = 0; i < totalDevices; i++) {
            boolean isGrouped = false;
            for (List<Integer> group : groupedDevices) {
                if (group.contains(i)) {
                    isGrouped = true;
                    break;
                }
            }
            if (!isGrouped) {
                ungroupedDevices.add(i);
            }
        }
        return ungroupedDevices;
    }
}
