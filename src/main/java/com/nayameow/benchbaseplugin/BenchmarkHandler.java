package com.nayameow.benchbaseplugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.io.InputStreamReader;

public class BenchmarkHandler {

    public static void execute() throws IOException, InterruptedException {

        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String benchbase = ".\\benchbase\\target\\benchbase-postgres\\";
        ProcessBuilder processBuilder = new ProcessBuilder();
//        processBuilder.command("cmd.exe","/c", "mvnw", "clean", "package", "-P", "postgres");
//        String commands = "cd .\\benchbase\\target\\benchbase-postgres";
        String commands = "cd benchbase\\target\\benchbase-postgres;" + javaBin + "-jar benchbase.jar -b tpcc -c .\\config\\postgres\\sample_tpcc_config.xml --create=true --load=true --execute=true";
//        processBuilder.command("cmd.exe","/c","cd benchbase\\target\\benchbase-postgres"; javaBin, "-jar", "benchbase.jar", "-b", "tpcc", "-c", benchbase + "config\\postgress\\sample_tpcc_config.xml", "--create=true", "--load=true", "--execute=true");
        processBuilder.command("cmd.exe", "/c", commands);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("Process exited with code : " + exitCode);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        execute();

    }
}
