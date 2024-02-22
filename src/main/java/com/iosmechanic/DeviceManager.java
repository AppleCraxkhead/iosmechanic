package com.iosmechanic;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DeviceManager {
    public Map<String, String> getDeviceInfo() {
        Map<String, String> parsedData = new HashMap<>();
        try {
            File executableFile = ResourceManager.extractExecutable("ideviceinfo");
            // Create ProcessBuilder instance
            ProcessBuilder pb = new ProcessBuilder(executableFile.getAbsolutePath());
            // Redirect error stream to output stream
            pb.redirectErrorStream(true);
            pb.directory(executableFile.getParentFile());
            // Start the process
            Process process = pb.start();
            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            

            reader.lines().forEach(line -> {
                if (line.contains(":")) {
                    String[] parts = line.split(":", 2);
                    String key = parts[0].trim();
                    String value = parts.length > 1 ? parts[1].trim() : "";
                    parsedData.put(key, value);
                }
            });
            int exitCode = process.waitFor();
            if(exitCode != 0){
                System.out.println("Error code " + exitCode);
            }
            return parsedData;
            /*while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print or process the output as needed
            }*/

            // Wait for the process to complete
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return parsedData;
    }
    public String getFromDeviceInfo(String property){
        Map<String, String> parsedData = getDeviceInfo();
        String result = parsedData.get(property);
        return result;
    }
}

