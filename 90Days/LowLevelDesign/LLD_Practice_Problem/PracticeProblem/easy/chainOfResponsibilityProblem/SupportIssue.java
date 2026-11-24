package PracticeProblem.easy.chainOfResponsibilityProblem;

import java.util.List;

public class SupportIssue {
    public static void main(String[] args) {

        SupportRouter router = new SupportRouter();

        List<String> result = router.submitIssue(ISSUETYPE.BUG);

        result.forEach(System.out::println);
    }
}
