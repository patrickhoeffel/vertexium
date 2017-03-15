package org.vertexium.test;

import org.vertexium.*;
import org.vertexium.event.*;
import org.vertexium.type.*;
import org.vertexium.util.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.google.common.collect.Lists;
import java.util.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    GeneralTestSuite.class
})
public abstract class GraphTestBase {
    private static final VertexiumLogger LOGGER = VertexiumLoggerFactory.getLogger(GraphTestBase.class);

    public static final String VISIBILITY_A_STRING = "a";
    public static final String VISIBILITY_B_STRING = "b";
    public static final String VISIBILITY_C_STRING = "c";
    public static final String VISIBILITY_MIXED_CASE_STRING = "MIXED_CASE_a";
    public static final Visibility VISIBILITY_A = new Visibility(VISIBILITY_A_STRING);
    public static final Visibility VISIBILITY_A_AND_B = new Visibility("a&b");
    public static final Visibility VISIBILITY_B = new Visibility("b");
    public static final Visibility VISIBILITY_MIXED_CASE_a = new Visibility("((MIXED_CASE_a))|b");
    public static final Visibility VISIBILITY_EMPTY = new Visibility("");
    public static final int LARGE_PROPERTY_VALUE_SIZE = 1024 * 1024 + 1;

    public static Authorizations AUTHORIZATIONS_A;
    public static Authorizations AUTHORIZATIONS_B;
    public static Authorizations AUTHORIZATIONS_C;
    public static Authorizations AUTHORIZATIONS_MIXED_CASE_a_AND_B;
    public static Authorizations AUTHORIZATIONS_A_AND_B;
    public static Authorizations AUTHORIZATIONS_EMPTY;
    public static Authorizations AUTHORIZATIONS_BAD;
    public static Authorizations AUTHORIZATIONS_ALL;
    public static boolean edgeBoostSupported;
    public static Graph graph;
    public static List<GraphEvent> graphEvents;

    protected abstract Graph createGraph() throws Exception;
    protected abstract Authorizations createAuthorizations(String... auths);

    public GraphTestBase() {
        AUTHORIZATIONS_A = createAuthorizations("a");
        AUTHORIZATIONS_B = createAuthorizations("b");
        AUTHORIZATIONS_C = createAuthorizations("c");
        AUTHORIZATIONS_A_AND_B = createAuthorizations("a", "b");
        AUTHORIZATIONS_MIXED_CASE_a_AND_B = createAuthorizations("MIXED_CASE_a", "b");
        AUTHORIZATIONS_EMPTY = createAuthorizations();
        AUTHORIZATIONS_BAD = createAuthorizations("bad");
        AUTHORIZATIONS_ALL = createAuthorizations("a", "b", "c", "MIXED_CASE_a");

        try {
            graph = createGraph();
            graphEvents = new ArrayList<>();
            graph.addGraphEventListener(new GraphEventListener() {
                @Override
                public void onGraphEvent(GraphEvent graphEvent) {
                    graphEvents.add(graphEvent);
                }
            });
        } catch (Exception e) {

        }
    }
}
