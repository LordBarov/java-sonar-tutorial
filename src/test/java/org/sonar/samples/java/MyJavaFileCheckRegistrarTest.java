/*
 * Copyright (C) 2012-2024 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
package org.sonar.samples.java;

import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;
import org.sonar.java.checks.verifier.TestCheckRegistrarContext;

import static org.assertj.core.api.Assertions.assertThat;

class MyJavaFileCheckRegistrarTest {

  @Test
  void checkRegisteredRulesKeysAndClasses() {
    TestCheckRegistrarContext context = new TestCheckRegistrarContext();

    MyJavaFileCheckRegistrar registrar = new MyJavaFileCheckRegistrar();
    registrar.register(context);

    assertThat(context.mainRuleKeys).extracting(RuleKey::toString).containsExactly(
      "omni-sonar:SpringControllerRequestMappingEntity",
      "omni-sonar:AvoidAnnotation",
      "omni-sonar:AvoidBrandInMethodNames",
      "omni-sonar:AvoidMethodDeclaration",
      "omni-sonar:AvoidSuperClass",
      "omni-sonar:AvoidTreeList",
      "omni-sonar:AvoidMethodWithSameTypeInArgument",
      "omni-sonar:SecurityAnnotationMandatory");

    assertThat(context.mainCheckClasses).extracting(Class::getSimpleName).containsExactly(
      "SpringControllerRequestMappingEntityRule",
      "AvoidAnnotationRule",
      "AvoidBrandInMethodNamesRule",
      "AvoidMethodDeclarationRule",
      "AvoidSuperClassRule",
      "AvoidTreeListRule",
      "MyCustomSubscriptionRule",
      "SecurityAnnotationMandatoryRule");

    assertThat(context.testRuleKeys).extracting(RuleKey::toString).containsExactly(
      "omni-sonar:NoIfStatementInTests");

    assertThat(context.testCheckClasses).extracting(Class::getSimpleName).containsExactly(
      "NoIfStatementInTestsRule");
  }

}
