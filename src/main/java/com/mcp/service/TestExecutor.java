package com.mcp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestExecutor {

	public static String runSuite(String suite) {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                "mvn", "test",
                "-DsuiteXmlFile=" + suite
            );

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();

            return exitCode == 0 ? "PASS" : "FAIL";

        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
