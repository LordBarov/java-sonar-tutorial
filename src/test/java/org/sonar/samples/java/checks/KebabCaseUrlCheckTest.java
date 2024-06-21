package org.sonar.samples.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class KebabCaseUrlCheckTest {

    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/KebabCaseUrlCheck.java")
                .withCheck(new KebabCaseUrlCheck())
                .verifyIssues();
    }
}
