grammar Tree;

tree: '(' ')'
    | '(' node (node | tree)* ')'
    ;

node
    : 'HEAD'
    | 'FUNC_DEF'
    | 'DECLARATION'
    | 'BLOCK'
    | 'EXPR_STAT'
    | 'ITER_STAT'
    | 'JUMP_STAT'
    | 'SEL_STAT'
    | 'SEL_CLAUSE'
    | 'STRUCT_SPEC'
    | 'STRUCT_DEC_LIST'
    | 'STRUCT_DEC'
    ;

WS : [ \t\r\n]+ -> skip ;