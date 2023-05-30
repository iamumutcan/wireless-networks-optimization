import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteHtml {
    public void  basestation(String devicelist, String basestaionlist){
        String htmlKod = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Koordinat Düzlemi</title>\n" +
                "  <style>\n" +
                "    #canvas {\n" +
                "      position: absolute;\n" +
                "      top: 50%;\n" +
                "      left: 50%;\n" +
                "      transform: translate(-50%, -50%);\n" +
                "      width: 1000px;\n" +
                "      height: 1000px;\n" +
                "      border: 1px solid black;\n" +
                "    }\n" +
                "    .device {\n" +
                "      position: absolute;\n" +
                "      width: 5px;\n" +
                "      height: 5px;\n" +
                "      background-color: blue;\n" +
                "      border-radius: 50%;\n" +
                "      z-index: 1;\n" +
                "      animation: blinkAnimation 1s infinite;"+
                "    }\n" +
                "    .station {\n" +
                "      position: absolute;\n" +
                "      width: 20px;\n" +
                "      height: 20px;\n" +
                "      background-color: red;\n" +
                "      border: 2px solid black;\n" +
                "      border-radius: 50%;\n" +
                "      opacity: 0.4;\n" +
                "      z-index: 0;\n" +
                "    }\n" +
                " @keyframes blinkAnimation {\n" +
                "      0% { opacity: 1; }\n" +
                "      50% { opacity: 0.3; }\n" +
                "      100% { opacity: 1; }\n" +
                "    }"+
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div id=\"canvas\"></div>\n" +
                "\n" +
                "  <script>\n" +
                "    function placeCoordinates() {\n" +
                "      const devices = [\n" +devicelist+
                "      ];\n" +
                "\n" +
                "      const stations = [\n" +basestaionlist+

                "      ];\n" +
                "\n" +
                "      const canvas = document.getElementById('canvas');\n" +
                "      const canvasWidth = canvas.offsetWidth;\n" +
                "      const canvasHeight = canvas.offsetHeight;\n" +
                "      const centerX = canvasWidth / 2;\n" +
                "      const centerY = canvasHeight / 2;\n" +
                "\n" +
                "      // Place stations\n" +
                "      stations.forEach(station => {\n" +
                "        const stationElement = document.createElement('div');\n" +
                "        stationElement.className = 'station';\n" +
                "     \n" +
                "        const stationX = centerX + station.x-(station.range);\n" +
                "        const stationY = centerY - station.y-(station.range);\n" +
                "        const stationRange = station.range * 2;\n" +
                "        stationElement.style.top = stationY + 'px';\n" +
                "        stationElement.style.left = stationX + 'px';\n" +
                "        stationElement.style.width = stationRange + 'px';\n" +
                "        stationElement.style.height = stationRange + 'px';\n" +
                "        canvas.appendChild(stationElement);\n" +
                "      });\n" +
                "\n" +
                "      // Place devices\n" +
                "      devices.forEach(device => {\n" +
                "        const deviceElement = document.createElement('div');\n" +
                "        deviceElement.className = 'device';\n" +
                "        const deviceX = centerX + device.x;\n" +
                "        const deviceY = centerY - device.y;\n" +
                "        deviceElement.style.top = deviceY + 'px';\n" +
                "        deviceElement.style.left = deviceX + 'px';\n" +
                "        canvas.appendChild(deviceElement);\n" +
                "  if (device.isConnected) {\n" +
                "        deviceElement.style.backgroundColor = '#05ee26';\n" +
                "    } else {\n" +
                "        deviceElement.style.backgroundColor = '#ffe59e';\n" +
                "    }"+
                "      });\n" +
                "    }\n" +
                "\n" +
                "    placeCoordinates();\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/index.html"));
            writer.write(htmlKod);
            writer.close();
            System.out.println("HTML dosyası oluşturuldu.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
