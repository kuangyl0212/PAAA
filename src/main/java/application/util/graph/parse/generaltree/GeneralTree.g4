grammar GeneralTree;

tree: '(' ')'
    | '(' node (node | tree)* ')'
    ;

node: NODE;

NODE: [a-zA-Z]+;

WS: [ \t\r\n]+ -> skip ;