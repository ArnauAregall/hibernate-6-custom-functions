package tech.aaregall.lab.hibernate6.persistance.functions;

import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.dialect.PostgreSQLSqlAstTranslator;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.sql.ast.spi.ParameterMarkerStrategy;
import org.hibernate.sql.ast.tree.SqlAstNode;
import org.hibernate.sql.ast.tree.Statement;
import org.hibernate.sql.ast.tree.expression.Literal;
import org.hibernate.sql.ast.tree.expression.LiteralAsParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArrayContainsSQLFunctionTest {

    @Mock
    private SessionFactoryImplementor mockSessionFactoryImplementor;

    @Mock
    private Statement mockStatement;

    @Mock
    private JdbcServices mockJdbcServices;

    @BeforeEach
    void setUp() {
        when(mockJdbcServices.getDialect())
                .thenReturn(mock(PostgreSQLDialect.class));
        when(mockJdbcServices.getParameterMarkerStrategy())
                .thenReturn(mock(ParameterMarkerStrategy.class));
        when(mockSessionFactoryImplementor.getJdbcServices())
                .thenReturn(mockJdbcServices);
    }

    @ParameterizedTest
    @MethodSource("renderNotTwoArguments")
    void render_When_NotTwoArguments_Then_ThrowsIllegalArgumentException(final List<? extends SqlAstNode> arguments) {
        final PostgreSQLSqlAstTranslator<?> sqlAstTranslator = new PostgreSQLSqlAstTranslator<>(mockSessionFactoryImplementor, mockStatement);

        final var function = new ArrayContainsSQLFunction("test_function");
        assertThatCode(() -> function.render(sqlAstTranslator, arguments, sqlAstTranslator))
                .isInstanceOf(IllegalArgumentException.class)
                .extracting(Throwable::getMessage)
                .isEqualTo("Function 'test_function' requires exactly 2 arguments");
    }

    @Test
    void render_When_TwoArguments_Then_ArrayContainsFunctionIsAppended() {
        final PostgreSQLSqlAstTranslator<?> sqlAstTranslator = new PostgreSQLSqlAstTranslator<>(mockSessionFactoryImplementor, mockStatement);

        final List<? extends SqlAstNode> arguments = List.of(
                givenMockLiteral("array_property"),
                givenMockLiteral("'value_to_find'")
        );

        new ArrayContainsSQLFunction("array_contains")
                .render(sqlAstTranslator, arguments, sqlAstTranslator);

        assertThat(sqlAstTranslator.getSqlBuffer().toString())
                .isEqualTo("(array_property @> ARRAY['value_to_find'])");
    }

    static Stream<Arguments> renderNotTwoArguments() {
        return Stream.of(
                arguments(emptyList()),
                arguments(singletonList(givenMockLiteral("some_property"))),
                arguments(List.of(
                        givenMockLiteral("some_property_1"),
                        givenMockLiteral("some_property_2"),
                        givenMockLiteral("some_property_3")
                ))
        );
    }

    private static LiteralAsParameter<Literal> givenMockLiteral(final String parameterMarker) {
        return new LiteralAsParameter<>(mock(Literal.class), parameterMarker);
    }

}
