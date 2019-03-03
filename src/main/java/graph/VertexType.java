package graph;

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

}
