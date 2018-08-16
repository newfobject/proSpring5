package ch8;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
public class AuditorAwareBean implements AuditorAware<String> {

    @Override
    @NotNull
    public Optional<String> getCurrentAuditor() {
        return Optional.of("prospring5");
    }
}
