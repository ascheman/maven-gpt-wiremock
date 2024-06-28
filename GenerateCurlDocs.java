///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 11
//DEPS com.fasterxml.jackson.core:jackson-databind:2.13.0

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerateCurlDocs {

    public static void main(String... args) {
        File mappingsDir = new File("./recordings/mappings");
        ObjectMapper mapper = new ObjectMapper();

        try (PrintWriter writer = new PrintWriter("curl-commands.adoc", "UTF-8")) {
            writer.println("= Curl Commands\n");
            writer.println("This document contains the curl commands derived from the WireMock recordings.\n");

            for (File file : mappingsDir.listFiles()) {
                JsonNode rootNode = mapper.readTree(file);
                JsonNode requestNode = rootNode.get("request");

                String method = requestNode.get("method").asText();
                String url = requestNode.get("url").asText();

                writer.println("[source,bash]");
                writer.println("----");
                writer.println("curl -X " + method + " http://localhost:8088" + url);
                writer.println("----\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}