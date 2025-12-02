package no.kaikka.aoc.utils;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Properties;

public class AOCUtils {
    public static void main(String[] args) {
        System.out.println(getInput());
    }

    public static String getInput() {
        int day = LocalDateTime.now(ZoneId.of("US/Eastern")).getDayOfMonth();
        return getInput(day);
    }

    public static String getInput(int day) {
        int year = LocalDateTime.now(ZoneId.of("US/Eastern")).getYear();
        return getInput(year, day);
    }

    public static String getInput(int year, int day) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/aoc.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String cookieValue = props.getProperty("cookie");
        String fileName = "src/main/resources/" + year + "-" + day + ".txt";

        String cachedString = readFromCachedFile(fileName);
        if (cachedString != null) {
            System.out.println("Using cached file: " + fileName);
            return cachedString;
        }

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://adventofcode.com/" + year + "/day/" + day + "/input"))
                    .header("Cookie", cookieValue)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                writeToFile(fileName, response.body());
                return response.body();
            } else {
                throw new RuntimeException(response.body());
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToFile(String fileName, String content) {
        try {
            System.out.println("Caching file: " + fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFromCachedFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
