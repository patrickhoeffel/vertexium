package org.vertexium.cypher.functions.list;

import org.vertexium.cypher.VertexiumCypherQueryContext;
import org.vertexium.cypher.ast.model.CypherAstBase;
import org.vertexium.cypher.exceptions.VertexiumCypherTypeErrorException;
import org.vertexium.cypher.executor.ExpressionScope;
import org.vertexium.cypher.functions.CypherFunction;

import java.util.List;

public class TailFunction extends CypherFunction {
    @Override
    public Object invoke(VertexiumCypherQueryContext ctx, CypherAstBase[] arguments, ExpressionScope scope) {
        assertArgumentCount(arguments, 1);
        Object arg0 = ctx.getExpressionExecutor().executeExpression(ctx, arguments[0], scope);

        if (arg0 instanceof List) {
            List list = (List) arg0;
            return list.subList(1, list.size());
        }

        throw new VertexiumCypherTypeErrorException(arg0, List.class);
    }
}
