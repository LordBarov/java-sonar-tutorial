import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

public class MyController {

    @GetMapping("/kebab-case-url") // Compliant
    public String getCompliant() {
        return "compliant";
    }

    @GetMapping("/camelCaseUrl") // Noncompliant
    public String getNonCompliant() {
        return "noncompliant";
    }

    @PostMapping("/another-kebab-case-url") // Compliant
    public void postCompliant() {
    }

    @PostMapping("/AnotherCamelCaseUrl") // Noncompliant
    public void postNonCompliant() {
    }

    @PutMapping("/yet-another-kebab-case-url") // Compliant
    public void putCompliant() {
    }

    @PutMapping("/YetAnotherCamelCaseUrl") // Noncompliant
    public void putNonCompliant() {
    }

    @DeleteMapping("/final-kebab-case-url") // Compliant
    public void deleteCompliant() {
    }

    @DeleteMapping("/FinalCamelCaseUrl") // Noncompliant
    public void deleteNonCompliant() {
    }
}