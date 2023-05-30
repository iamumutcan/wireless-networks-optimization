import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Opt {
    public static void main(String[] args) throws FileNotFoundException {
        List<Device> deviceList = new ArrayList<>();
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
            deviceList.add(new Device(index,x, y));
            devicestring+="{ x:"+x+", y: "+y+" },";
            index++;
        }
        scanner.close();
        List<Group> groupsList = new ArrayList<>(); // geçici cihaz listesi oluşturulur
        int gindex=1;

        for (BaseStation baseStation : baseStationList) { // gruplanmamış istasyon seçilir
            double middlepointx=0;
            double middlepointy=0;
            List<Device> tempDeviceList = new ArrayList<>();
          if(baseStation.isGrouped==false)
          {
               // geçici cihaz listesi oluşturulur
              for (Device device : deviceList) {
                  double sumx=0;
                  double sumy=0;
                  if(device.isConnected==false&&tempDeviceList.size()<baseStation.maxCapacity) { // cihaz bağlanmadıysa
                    if(tempDeviceList.isEmpty()){ // geçici cihaz listesi boşsa ilk veriyi ekler
                        device.isConnected=true;
                        baseStation.isGrouped=true;
                        System.out.println("kordinatları şu olan cihaz eklendi: " + device.x+","+device.y);
                        System.out.println("baz istasyonu özelikleri: " + baseStation.maxCapacity+","+baseStation.coverageRadius);
                        sumx=device.x;
                        sumy=device.y;
                        middlepointx=sumx;
                        middlepointy=sumy;
                        tempDeviceList.add(device);

                    }
                    else { // yeni veri eklerken ortalamaya uygun mu diye hesaplanır
                        List<Device> tempDeviceList2 = new ArrayList<>(); // geçici cihaz listesi oluşturulur
                        tempDeviceList2=tempDeviceList;

                        for (Device tempdevice : tempDeviceList){
                            sumx+=tempdevice.x;
                            sumy+=tempdevice.y;
                        }
                        tempDeviceList2.add(device);
                        int totalDevice=tempDeviceList2.size();
                        sumx+=device.x;
                        sumy+=device.y;
                         middlepointx=sumx/totalDevice;
                         middlepointy=sumy/totalDevice;
                        for (Device tempdevice : tempDeviceList2){
                           double distance= calculateDistance(tempdevice.x,tempdevice.y,middlepointx,middlepointy);
                            if(distance<=baseStation.coverageRadius)
                            {
                                device.isConnected=true;
                                baseStation.isGrouped=true;
                                System.out.println("kordinatları şu olan cihaz eklendi: " + device.x+","+device.y);
                                System.out.println("baz istasyonu özelikleri: " + baseStation.maxCapacity+","+baseStation.coverageRadius);
                                tempDeviceList=tempDeviceList2;
                            }
                        }
                    }

                  }
              }

          }
            System.out.println(tempDeviceList.toString());
            basestationstring+="{ x:"+middlepointx+", y: "+middlepointy+", range: "+baseStation.coverageRadius+" },";
            groupsList.add(new Group(gindex,middlepointx,middlepointy,baseStation.maxCapacity,baseStation.coverageRadius,tempDeviceList));
            gindex++;
        }


        List<List<Device>> groupedDevices = groupDevices(deviceList, 3);

        // Gruplanmış cihazları yazdırma
        for (List<Device> group : groupedDevices) {
            System.out.println("Grup:");
            for (Device device : group) {
                System.out.println(device.x + " " + device.y);
            }
            System.out.println();
        }
        // Liste üzerindeki cihazları erişme ve kullanma
        for (Device device : deviceList) {
            System.out.println("X Coordinate: " + device.x);
            System.out.println("Y Coordinate: " + device.y);
            System.out.println("Is Connected: " + device.isConnected);
            System.out.println();
        }

        for (BaseStation baseStation : baseStationList) {
            System.out.println("X kapasite: " + baseStation.maxCapacity);
            System.out.println("X radious: " + baseStation.coverageRadius);
            System.out.println("Is Connected: " + baseStation.isGrouped);
            System.out.println();
        }

        for (Group group : groupsList) {
            System.out.println("Grup Id : " + group.id);
            System.out.println("Grup X : " + group.x);
            System.out.println("Grup Y : " + group.y);
            System.out.println("Grup Kapasite : " + group.maxCapacity);
            System.out.println("Grup Yarı çap : " + group.coverageRadius);
            System.out.println("Grup Liste : " + group.connectedDevices.toString());


            System.out.println();
        }

        WriteHtml whtml= new WriteHtml();
        whtml.basestation(devicestring,basestationstring);



    }
    public static List<List<Device>> groupDevices(List<Device> deviceList, int numGroups) {
        List<List<Device>> groupedDevices = new ArrayList<>();

        // Cihazları en yakın kordinatlara göre gruplama
        for (Device device : deviceList) {
            boolean addedToGroup = false;
            List<Device> closestGroup = null;
            double closestDistance = Double.MAX_VALUE;

            // Mevcut gruplardan en yakın olanını bulma
            for (List<Device> group : groupedDevices) {
                double groupDistance = getAverageDistance(device, group);
                if (groupDistance < closestDistance) {
                    closestGroup = group;
                    closestDistance = groupDistance;
                }
            }

            // En yakın grup bulunduysa cihazı ekleyerek gruplama yapma
            if (closestGroup != null) {
                closestGroup.add(device);
                addedToGroup = true;
            }

            // Hiçbir gruba eklenmediyse yeni bir grup oluşturma
            if (!addedToGroup) {
                List<Device> newGroup = new ArrayList<>();
                newGroup.add(device);
                groupedDevices.add(newGroup);
            }
        }

        // Grup sayısı 3'ten küçükse, grupları birleştirme
        while (groupedDevices.size() > numGroups) {
            mergeClosestGroups(groupedDevices);
        }

        return groupedDevices;
    }

    public static double getAverageDistance(Device device, List<Device> group) {
        double totalDistance = 0;
        int numDevices = group.size();

        // Grubun cihazlarıyla olan ortalama mesafeyi hesaplama
        for (Device groupDevice : group) {
            double distance = Math.sqrt(Math.pow(device.x - groupDevice.x, 2) + Math.pow(device.y - groupDevice.y, 2));
            totalDistance += distance;
        }

        return totalDistance / numDevices;
    }

    public static void mergeClosestGroups(List<List<Device>> groupedDevices) {
        List<Device> closestGroup1 = null;
        List<Device> closestGroup2 = null;
        double closestDistance = Double.MAX_VALUE;

        // En yakın iki grubu bulma
        for (int i = 0; i < groupedDevices.size(); i++) {
            for (int j = i + 1; j < groupedDevices.size(); j++) {
                double groupDistance = getAverageDistanceBetweenGroups(groupedDevices.get(i), groupedDevices.get(j));
                if (groupDistance < closestDistance) {
                    closestGroup1 = groupedDevices.get(i);
                    closestGroup2 = groupedDevices.get(j);
                    closestDistance = groupDistance;
                }
            }
        }

        // En yakın iki grubu birleştirme
        closestGroup1.addAll(closestGroup2);
        groupedDevices.remove(closestGroup2);
    }

    public static double getAverageDistanceBetweenGroups(List<Device> group1, List<Device> group2) {
        double totalDistance = 0;
        int numDevices1 = group1.size();
        int numDevices2 = group2.size();

        // İki grup arasındaki cihazların ortalama mesafesini hesaplama
        for (Device device1 : group1) {
            for (Device device2 : group2) {
                double distance = Math.sqrt(Math.pow(device1.x - device2.x, 2) + Math.pow(device1.y - device2.y, 2));
                totalDistance += distance;
            }
        }

        return totalDistance / (numDevices1 * numDevices2);
    }
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return distance;
    }
}


