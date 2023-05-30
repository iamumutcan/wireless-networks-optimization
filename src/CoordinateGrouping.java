import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CoordinateGrouping {
    public static void main(String[] args) throws FileNotFoundException {
        List<Device> deviceList = new ArrayList<>();
        Map<Integer, Device> deviceMap = new HashMap<>();
        List<Group> baseStationDeviceList= new ArrayList<>();
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
        Collections.sort(baseStationList, Comparator.comparingDouble(BaseStation::getCoverageRadius).reversed());


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
            index++;
        }
        scanner.close();


        int gindex=1;
        for (BaseStation baseStation : baseStationList) { // gruplanmamış istasyon seçilir
            double middlepointx = 0;
            double middlepointy = 0;
            double sumx=0;
            double sumy=0;
            List<Device> tempDeviceList = new ArrayList<>();
            if (baseStation.isGrouped == false) {
                double threshold = baseStation.coverageRadius; // Yakınlık eşiği
                int maxCapacity=baseStation.maxCapacity;
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

                        sumx+=device.x;
                        sumy+=device.y;
                    }
                }
                 middlepointx = sumx/maxGroupSize;
                 middlepointy = sumy/maxGroupSize;
                System.out.println("En çok elemana sahip olan grup: " + maxGroupSize);
                System.out.println("Grup: " + maxGroup);
                System.out.println("X: "+middlepointx +" Y: "+middlepointy);

            }
            basestationstring+="{ x:"+middlepointx+", y: "+middlepointy+", range: "+baseStation.coverageRadius+" },";
            baseStationDeviceList.add(new Group(gindex,middlepointx,middlepointy,baseStation.maxCapacity,baseStation.coverageRadius,tempDeviceList));
            gindex++;
        }
        for (Device device : deviceList) {
            System.out.println(device);
            devicestring+="{ x:"+device.x+", y: "+device.y+",isConnected:"+device.isConnected+" },";
        }
        WriteHtml whtml= new WriteHtml();
        whtml.basestation(devicestring,basestationstring);
    }

    public static Map<Integer, List<Device>> groupCoordinates(List<Device> deviceList, double threshold, int maxCapacity) {
        Map<Integer, List<Device>> groups = new HashMap<>();
        int groupId = 0;

        for (Device device : deviceList) {
            if(device.isConnected==false)
            {
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

        }

        return groups;
    }

    public static double getDistance(Device device1, Device device2) {
        double dx = device1.x - device2.x;
        double dy = device1.y - device2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
