package application.grade;

import graph.CDG;
import graph.Vertex;

import java.util.Iterator;
import java.util.function.BinaryOperator;

public class Scale {
    public int functionDefinition = 0;
    public int declaration = 0;
    public int expressionStatement = 0;
    public int selectionStatement = 0;
    public int iterationStatement = 0;
    public int jumpStatement = 0;
    public int selectionClause = 0;
    public int structSpecifier = 0;
    public int structDeclarationList = 0;
    public int structDeclaration = 0;
    public int block = 0;

    public Scale(CDG cdg) {
        for (Iterator<Vertex> it = cdg.vertexSet().iterator(); it.hasNext(); ) {
            countType(it.next());
        }
    }

    private void countType(Vertex vertex) {
        switch (vertex.getType()) {
            case FUNC_DEF:
                functionDefinition++;   break;
            case DECLARATION:
                declaration++;          break;
            case EXPR_STAT:
                expressionStatement++;  break;
            case SEL_STAT:
                selectionStatement++;   break;
            case ITER_STAT:
                iterationStatement++;   break;
            case SEL_CLAUSE:
                selectionClause++;      break;
            case JUMP_STAT:
                jumpStatement++;        break;
            case STRUCT_SPEC:
                structSpecifier++;      break;
            case STRUCT_DEC_LIST:
                structDeclarationList++;break;
            case STRUCT_DEC:
                structDeclaration++;    break;
            case BLOCK:
                block++;                break;
            default:                    break;
        }
    }
}
