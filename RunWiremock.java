//usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 11
//DEPS com.github.tomakehurst:wiremock-jre8-standalone:2.31.0

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;

public class RunWiremock {

    public static void main(String... args) {
        WireMockServerRunner.main("--port", "8088",
                "--verbose",
                "--root-dir",
                "./recordings"
        );
    }
}