package PracticeProblem.hard.BuilderPattern;

import java.util.ArrayList;
import java.util.List;

class ReportSection {
    final String heading;
    final String content;

    ReportSection(String heading, String content) {
        this.heading = heading;
        this.content = content;
    }
}

class ReportDoc {
    private final String title;
    private final String subtitle;
    private final String footer;
    private final List<ReportSection> sections;
    private final List<String> charts;

    ReportDoc(ReportBuilder builder) {
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.footer = builder.footer;
        this.sections = new ArrayList<>(builder.sections);
        this.charts = new ArrayList<>(builder.charts);
    }

    String render() {
        List<String> numbered = new ArrayList<>();
        for (int i = 0; i < sections.size(); i++) {
            ReportSection section = sections.get(i);
            numbered.add((i + 1) + ". " + section.heading + ": " + section.content);
        }
        return "Report{title='" + title
                + "', subtitle='" + subtitle
                + "', sections=[" + String.join(" | ", numbered)
                + "], charts=[" + String.join(", ", charts)
                + "], footer='" + footer + "'}";
    }
}

// Implement the complete ReportBuilder class here.
class ReportBuilder{
    public  String title;
    public String subtitle;
    public String footer;
    public List<ReportSection> sections = new ArrayList<>();
    public List<String> charts = new ArrayList<>();

    public ReportBuilder(String title){
        this.title = title;
        this.subtitle = "";
        this.footer = "";
    }

    public ReportBuilder setSubtitle(String text){
        this.subtitle = text;
        return this;
    }

    public ReportBuilder setFooter(String text){
        this.footer = text;
        return this;
    }

    public ReportBuilder addSection(String heading, String content){
        this.sections.add(new ReportSection(heading,content));
        return this;
    }

    public ReportBuilder addChart(String name){
        this.charts.add(name);
        return this;
    }

    public int sectionCount(){
        return sections.size();
    }

    public ReportDoc build(){
        if (sections.isEmpty()) {
            return null;
        }
        return new ReportDoc(this);
    }

}

class ReportComposer {
    private ReportBuilder draft = null;
    private final List<ReportDoc> built = new ArrayList<>();

    public ReportComposer() {
    }

    public boolean start(String title) {
        if (title.isEmpty()) {
            return false;
        }
        draft = new ReportBuilder(title);
        return true;
    }

    public boolean subtitle(String text) {
        if (draft == null) {
            return false;
        }
        draft.setSubtitle(text);
        return true;
    }

    public boolean addSection(String heading, String content) {
        if (draft == null) {
            return false;
        }
        draft.addSection(heading, content);
        return true;
    }

    public boolean addChart(String name) {
        if (draft == null) {
            return false;
        }
        draft.addChart(name);
        return true;
    }

    public boolean footer(String text) {
        if (draft == null) {
            return false;
        }
        draft.setFooter(text);
        return true;
    }

    public boolean discard() {
        if (draft == null) {
            return false;
        }
        draft = null;
        return true;
    }

    public String build() {
        if (draft == null) {
            return "Error: no report started";
        }
        ReportDoc report = draft.build();
        if (report == null) {
            return "Error: Report must have at least one section";
        }
        built.add(report);
        return report.render();
    }

    public String rendered(int index) {
        if (index < 0 || index >= built.size()) {
            return "ERROR: no such report";
        }
        return built.get(index).render();
    }

    public int sectionCount() {
        if (draft == null) {
            return 0;
        }
        return draft.sectionCount();
    }

    public int buildCount() {
        return built.size();
    }
}
