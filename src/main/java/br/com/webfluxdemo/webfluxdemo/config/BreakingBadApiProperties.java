package br.com.webfluxdemo.webfluxdemo.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "breaking-bad-api")
@Getter
@Setter
@NoArgsConstructor
public class BreakingBadApiProperties {

    private String url;
}
