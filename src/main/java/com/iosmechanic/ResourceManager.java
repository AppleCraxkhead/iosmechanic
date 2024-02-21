package com.iosmechanic;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class ResourceManager {

    public static File extractExecutable(String action) throws IOException {
        // Extract the main executable
        File executable = extractResource("/libimobiledevice/" + action + ".exe", ".exe");

        // Assuming DLLs are known and listed here
        List<String> dllNames = List.of(
    "libbrotlicommon.dll",
    "libbrotlidec.dll",
    "libbrotlienc.dll",
    "libbz2-1.dll",
    "libcrypto-3-x64.dll",
    "libcurl-4.dll",
    "libiconv-2.dll",
    "libideviceactivation-1.0.dll",
    "libidn2-0.dll",
    "libimobiledevice-1.0.dll",
    "libimobiledevice-glue-1.0.dll",
    "libintl-8.dll",
    "libirecovery-1.0.dll",
    "liblzma-5.dll",
    "libnghttp2-14.dll",
    "libplist++-2.0.dll",
    "libplist-2.0.dll",
    "libpsl-5.dll",
    "libreadline8.dll",
    "libssh2-1.dll",
    "libssl-3-x64.dll",
    "libtermcap-0.dll",
    "libunistring-5.dll",
    "libusbmuxd-2.0.dll",
    "libxml2-2.dll",
    "libzip.dll",
    "libzstd.dll",
    "zlib1.dll"
);
    for (String dllName : dllNames) {
        extractResource("/libimobiledevice/" + dllName, ".dll");
}


        return executable;
    }

    private static File extractResource(String resourcePath, String suffix) throws IOException {
        InputStream inputStream = ResourceManager.class.getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new FileNotFoundException("Resource not found: " + resourcePath);
        }
        
        // Create a temporary directory if not exists
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "libimobiledevice");
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        
        // Extract the filename from the resource path and create a new file in the temp directory
        String fileName = new File(resourcePath).getName();
        File tempFile = new File(tempDir, fileName);
        
        Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        tempFile.deleteOnExit();
        return tempFile;
    }}