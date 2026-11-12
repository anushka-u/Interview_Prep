package SingletonDesignPattern;

public class ConfigurationManager {

    private String configValue;

    private ConfigurationManager(){
        IO.print("ConfigurationManager instance created.");
        configValue = "DefaultConfig";
    }

    private static class Holder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }

    public static ConfigurationManager getInstance(){
        return Holder.INSTANCE;
    }

    public String getConfigValue() {
        return configValue;
    }
}
