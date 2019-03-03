package graph;

import org.antlr.v4.runtime.ParserRuleContext;

import java.io.Serializable;

public class Vertex implements Serializable {
    private VertexType type;
    private ParserRuleContext ctx;

    public Vertex(VertexType type) {
        this.type = type;
    }
    public VertexType getType() {
        return type;
    }

    public void setCtx(ParserRuleContext ctx) {
        this.ctx = ctx;
    }
    public ParserRuleContext getCtx() {return ctx;}

}
