package tech.aaregall.lab.hibernate6.persistence.functions;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;

public class CustomFunctionsContributor implements FunctionContributor {

    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        functionContributions.getFunctionRegistry()
                .register("array_contains", new ArrayContainsSQLFunction("array_contains"));
    }
}
