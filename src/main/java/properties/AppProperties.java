package properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface AppProperties extends Config {
    //Default Setup
    @Key("host.uri")
    String hostUri();
}

