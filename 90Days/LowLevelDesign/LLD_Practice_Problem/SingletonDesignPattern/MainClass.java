package SingletonDesignPattern;



public class MainClass {

    void main(){
        ConfigurationManager  configManager = ConfigurationManager.getInstance();
        IO.print(configManager.getConfigValue());

        ConfigurationManager configManager2 = ConfigurationManager.getInstance();
        IO.print(configManager2.getConfigValue());

        ConfigurationManager configManager3 = ConfigurationManager.getInstance();
        IO.print(configManager3.getConfigValue());
    }
}
