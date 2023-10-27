grammar LPP;
expr: ID
    | expr ID
    ;
ID : [a-zA-Z$_] [a-zA-Z0-9$_]* ;