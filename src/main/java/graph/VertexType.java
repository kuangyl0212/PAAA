package graph;

public enum VertexType {
    HEAD,
    FUNC_DEF,
    DECLARATION,
    BLOCK,
    EXPR_STAT,
    ITER_STAT,
    JUMP_STAT,
    SEL_STAT;

    @Override
    public String toString() {
        // maybe some fancy layout style?
        return super.toString();
    }
}
