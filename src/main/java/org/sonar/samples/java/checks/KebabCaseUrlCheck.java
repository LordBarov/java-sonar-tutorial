package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.util.List;
import java.util.regex.Pattern;

@Rule(key = "KebabCaseUrlCheck")
public class KebabCaseUrlCheck extends BaseTreeVisitor implements JavaFileScanner {

    private static final Pattern KEBAB_CASE_PATTERN = Pattern.compile("^(/[a-z0-9]+(-[a-z0-9]+)*)*$");

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitMethod(MethodTree tree) {
        List<AnnotationTree> annotations = tree.modifiers().annotations();
        for (AnnotationTree annotationTree : annotations) {
            TypeTree annotationType = annotationTree.annotationType();
            if (annotationType.is(Tree.Kind.IDENTIFIER)) {
                IdentifierTree identifier = (IdentifierTree) annotationType;
                String annotationName = identifier.name();
                if (annotationName.equals("GetMapping") || annotationName.equals("PostMapping") ||
                        annotationName.equals("PutMapping") || annotationName.equals("DeleteMapping")) {
                    checkUrl(annotationTree);
                }
            }
        }
        super.visitMethod(tree);
    }

    private void checkUrl(AnnotationTree annotationTree) {
        if (annotationTree.arguments().isEmpty()) {
            return;
        }

        Tree argument = annotationTree.arguments().get(0);
        if (argument.is(Tree.Kind.ASSIGNMENT)) {
            argument = ((org.sonar.plugins.java.api.tree.AssignmentExpressionTree) argument).expression();
        }

        if (argument.is(Tree.Kind.STRING_LITERAL)) {
            LiteralTree literal = (LiteralTree) argument;
            String url = literal.value().substring(1, literal.value().length() - 1);

            if (!KEBAB_CASE_PATTERN.matcher(url).matches()) {
                context.reportIssue(this, literal, "The URL should be in kebab-case.");
            }
        }
    }
}
