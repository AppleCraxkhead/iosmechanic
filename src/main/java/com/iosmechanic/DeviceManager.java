package com.iosmechanic;
import java.io.*;


public class DeviceManager {
    public void getDeviceInfo() {
        try {
            File executableFile = ResourceManager.extractExecutable("ideviceinfo");
            System.out.println("successfully finished resource management");
            // Create ProcessBuilder instance
            ProcessBuilder pb = new ProcessBuilder(executableFile.getAbsolutePath());
            // Redirect error stream to output stream
            pb.redirectErrorStream(true);
            pb.directory(executableFile.getParentFile());
            // Start the process
            Process process = pb.start();
            System.out.println("Process started");
            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print or process the output as needed
            }
            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Exited with error code " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
