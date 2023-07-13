package uom.mosip.attendanceservice.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class AuthRouteMatcher {

    private final List<PathPattern> patterns;

    public AuthRouteMatcher() {
        List<String> protectedEndpoints = Arrays.asList(
                "/rand/a/**",
                "/pend"
        );

        PathPatternParser parser = new PathPatternParser();
        List<PathPattern> patterns = new LinkedList<>();
        for (String endpoint: protectedEndpoints){
            patterns.add(parser.parse(endpoint));
        }
        this.patterns = patterns;
    }

    public List<PathPattern> getPatterns() {
        return patterns;
    }
}
