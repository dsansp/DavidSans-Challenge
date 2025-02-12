package util;

import org.aeonbits.owner.Config;
@Config.Sources("classpath:ServerConfig.properties")
public interface ServerConfig extends Config {
    @Key("url")
    String url();
    @Key("urlWeb")
    String urlWeb();
    @Key("servicePokemonList")
    String servicePokemonList();
    @Key("servicePokemonAbility")
    String servicePokemonAbility();
}
