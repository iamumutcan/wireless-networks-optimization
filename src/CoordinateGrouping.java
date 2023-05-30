import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CoordinateGrouping {
    public static void main(String[] args) throws FileNotFoundException {
        List<Device> deviceList = new ArrayList<>();
        Map<Integer, Device> deviceMap = new HashMap<>();

        List<BaseStation> baseStationList = new ArrayList<>();
        // Yeni cihazları liste üzerinde tutma
        int index = 0;
        String devicestring="";
        String basestationstring="";
        index = 0;
        File dosya2 = new File("src/BaseStationList");
        Scanner scannerBaseStationList = new Scanner(dosya2);
        while (scannerBaseStationList.hasNextLine()) {
            String satir = scannerBaseStationList.nextLine();
            String[] veriler = satir.split(" ");
            int x = Integer.parseInt(veriler[0]);
            double y = Double.parseDouble(veriler[1]);
            baseStationList.add(new BaseStation(index,x, y));
            index++;
        }
        scannerBaseStationList.close();

        index = 0;
        File dosya = new File("src/DeviceList");
        Scanner scanner = new Scanner(dosya);
        while (scanner.hasNextLine()) {
            String satir = scanner.nextLine();
            String[] veriler = satir.split(" ");
            double x = Double.parseDouble(veriler[0]);
            double y = Double.parseDouble(veriler[1]);
            Device device = new Device(index, x, y);
            deviceList.add(device);
            deviceMap.put(index, device);

            devicestring+="{ x:"+x+", y: "+y+" },";
            index++;
        }
        scanner.close();

        double threshold = 80.0; // Yakınlık eşiği
        int maxCapacity=5;
        Map<Integer, List<Device>> groups = groupCoordinates(deviceList, threshold,maxCapacity);

        int maxGroupSize = 0;
        List<Device> maxGroup = null;

        for (List<Device> group : groups.values()) {
            if (group.size() > maxGroupSize) {
                maxGroupSize = group.size();
                maxGroup = group;
            }
        }

        if (maxGroup != null) {
            for (Device device : maxGroup) {
                device.isConnected = true;
            }
        }

        System.out.println("En çok elemana sahip olan grup: " + maxGroupSize);
        System.out.println("Grup: " + maxGroup);
        System.out.println("Cihazlar:");
        for (Device device : deviceList) {
            System.out.println(device);
        }
    }

    public static Map<Integer, List<Device>> groupCoordinates(List<Device> deviceList, double threshold, int maxCapacity) {
        Map<Integer, List<Device>> groups = new HashMap<>();
        int groupId = 0;

        for (Device device : deviceList) {
            boolean foundGroup = false;
            for (List<Device> group : groups.values()) {
                Device reference = group.get(0);
                double distance = getDistance(device, reference);

                if (distance <= threshold&&group.size()<maxCapacity) {
                    group.add(device);
                    foundGroup = true;
                    break;
                }
            }
            if (!foundGroup) {
                List<Device> newGroup = new ArrayList<>();
                newGroup.add(device);
                groups.put(groupId, newGroup);
                groupId++;
            }
        }

        return groups;
    }

    public static double getDistance(Device device1, Device device2) {
        double dx = device1.x - device2.x;
        double dy = device1.y - device2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
