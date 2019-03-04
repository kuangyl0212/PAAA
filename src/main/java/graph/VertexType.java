package graph;

import exception.InvalidVertexType;

public enum VertexType {
    HEAD,               /* start rule                   */
    FUNC_DEF,           /* function definition          */
    DECLARATION,        /* declaration                  */
    BLOCK,              /* block statement list         */
    EXPR_STAT,          /* expression statement         */
    ITER_STAT,          /* iteration statement          */
    JUMP_STAT,          /* jump statement               */
    SEL_STAT,           /* selection statement          */
    SEL_CLAUSE,         /* selection clause             */
    STRUCT_SPEC,        /* struct or union specifier    */
    STRUCT_DEC_LIST,    /* struct declaration list      */
    STRUCT_DEC,         /* struct declaration           */
    ;

    public static VertexType getTypeFrom(String string) throws Throwable {
        switch (string) {
            case "HEAD":            return HEAD;
            case "FUNC_DEF":        return FUNC_DEF;
            case "DECLARATION":     return DECLARATION;
            case "BLOCK":           return BLOCK;
            case "EXPR_STAT":       return EXPR_STAT;
            case "ITER_STAT":       return ITER_STAT;
            case "JUMP_STAT":       return JUMP_STAT;
            case "SEL_STAT":        return SEL_STAT;
            case "SEL_CLAUSE":      return SEL_CLAUSE;
            case "STRUCT_SPEC":     return STRUCT_SPEC;
            case "STRUCT_DEC_LIST": return STRUCT_DEC_LIST;
            case "STRUCT_DEC":      return STRUCT_DEC;
        }
        throw new InvalidVertexType();
    }

}
