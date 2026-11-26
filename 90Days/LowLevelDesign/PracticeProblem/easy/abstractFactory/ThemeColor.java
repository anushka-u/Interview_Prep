package easy.abstractFactory;

interface ThemeColor {
    String themeName();
    String apply();
}

interface ThemeFont {
    String themeName();
    String render();
}

class LightColor implements ThemeColor {
    @Override
    public String themeName() {
        return "light";
    }

    @Override
    public String apply() {
        return "Applying light color: #FFFFFF background, #000000 text";
    }
}

class DarkColor implements ThemeColor {
    @Override
    public String themeName() {
        return "dark";
    }

    @Override
    public String apply() {
        return "Applying dark color: #1E1E1E background, #FFFFFF text";
    }
}

class LightFont implements ThemeFont {
    @Override
    public String themeName() {
        return "light";
    }

    @Override
    public String render() {
        return "Rendering light theme font: Arial, 14px";
    }
}

class DarkFont implements ThemeFont {
    @Override
    public String themeName() {
        return "dark";
    }

    @Override
    public String render() {
        return "Rendering dark theme font: Consolas, 14px";
    }
}

// Implement the ThemeFactory interface here.
interface ThemeFactory{
    String themeName();
    ThemeColor createColor();
    ThemeFont createFont();

}
// Implement the LightThemeFactory class here.
class LightThemeFactory implements ThemeFactory{

    @Override
    public String themeName(){
        return "light";
    }

    @Override
    public ThemeColor createColor(){
        return new LightColor();
    }

    @Override
    public ThemeFont createFont(){
        return new LightFont();
    }
}
// Implement the DarkThemeFactory class here.
class DarkThemeFactory implements ThemeFactory{
    @Override
    public String themeName(){
        return "dark";
    }

    @Override
    public ThemeColor createColor(){
        return new DarkColor();
    }

    @Override
    public ThemeFont createFont(){
        return new DarkFont();
    }
}
// ThemeManager is pre-implemented. Do not change it.
class ThemeManager {
    private String theme;
    private ThemeColor color;
    private ThemeFont font;
    private int switches = 0;

    public ThemeManager() {
        useFactory(new LightThemeFactory());
    }

    private ThemeFactory factoryFor(String name) {
        if (name.equals("light")) {
            return new LightThemeFactory();
        }
        if (name.equals("dark")) {
            return new DarkThemeFactory();
        }
        return null;
    }

    private void useFactory(ThemeFactory factory) {
        theme = factory.themeName();
        color = factory.createColor();
        font = factory.createFont();
    }

    public boolean setTheme(String name) {
        ThemeFactory factory = factoryFor(name);
        if (factory == null) {
            return false;
        }
        useFactory(factory);
        switches++;
        return true;
    }

    public String currentTheme() {
        return theme;
    }

    public String colorLine() {
        return color.apply();
    }

    public String fontLine() {
        return font.render();
    }

    public String partsReport() {
        return "color=" + color.themeName() + ", font=" + font.themeName();
    }

    public int switchCount() {
        return switches;
    }
}