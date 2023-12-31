package tech.aaregall.lab.hibernate6.persistence.functions;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.sql.ast.SqlAstTranslator;
import org.hibernate.sql.ast.spi.SqlAppender;
import org.hibernate.sql.ast.tree.SqlAstNode;
import org.hibernate.type.BasicTypeReference;
import org.hibernate.type.SqlTypes;

import java.util.List;

class ArrayContainsSQLFunction extends StandardSQLFunction {

    private static final BasicTypeReference<Boolean> RETURN_TYPE = new BasicTypeReference<>("boolean", Boolean.class, SqlTypes.BOOLEAN);

    ArrayContainsSQLFunction(final String functionName) {
        super(functionName, true, RETURN_TYPE);
    }

    @Override
    public void render(SqlAppender sqlAppender, List<? extends SqlAstNode> arguments, SqlAstTranslator<?> translator) {
        if (arguments.size() != 2) {
            throw new IllegalArgumentException(STR."Function '\{getName()}' requires exactly 2 arguments");
        }

        sqlAppender.append("(");
        arguments.get(0).accept(translator);
        sqlAppender.append(" @> ARRAY[");
        arguments.get(1).accept(translator);
        sqlAppender.append("])");
    }

}
