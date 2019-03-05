package application.grade;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;

import java.util.ArrayList;

import static graph.VertexType.*;

class VertexTypeCount {
    int iterations = 0;
    int selections = 0;
    int functionDefinitions = 0;
    int other = 0;

    @Override
    public String toString() {
        return "iterations: \t" + iterations + "\n"
                + "selections: \t" + selections + "\n"
                + "functionDefinitions: \t" + functionDefinitions + "\n"
                + "other: \t" + other + "\n";
    }
}

public class StructureMatcher {

    private CDG paired;
    private int vertexMatched;
    private VertexTypeCount matchedTypeCount;
    private VertexTypeCount vertexTypeCountOfStd;

    /**
     *
     * Please note that the order of these two param do matters the result!
     * @param CDG_OfStd The graph generated from standard answer
     * @param CDG_OfStu The graph generated from student's answer
     */
    public StructureMatcher(CDG CDG_OfStd, CDG CDG_OfStu) {

        StructureExtractor extractorOfStd = new StructureExtractor(CDG_OfStd);
        StructureExtractor extractorOfStu = new StructureExtractor(CDG_OfStu);

        CDG CDG_NormalOfStd = extractorOfStd.get();
        CDG CDG_NormalOfStu = extractorOfStu.get();

        vertexTypeCountOfStd = countVertexTypeOf(CDG_NormalOfStd);

        paired = new CDG();
        paired.addVertex(new Vertex(HEAD));
        Vertex headOfStd = extractorOfStd.get().getHead();
        Vertex headOfStu = extractorOfStu.get().getHead();
        vertexMatched = match(headOfStu, CDG_NormalOfStu,  headOfStd, CDG_NormalOfStd, paired);

        matchedTypeCount = countVertexTypeOf(paired);

    }

    int howManyVertexesMatched() {
        return vertexMatched;
    }

    CDG whichVertexesMatched() {
        return paired;
    }

    private VertexTypeCount countVertexTypeOf(CDG absCDG) {
        VertexTypeCount typeCount = new VertexTypeCount();
        // do something
        for (Vertex v : absCDG.vertexSet()) {
            VertexType type = v.getType();
            switch (type) {
                case HEAD:
                    break;
                case ITER_STAT:
                    typeCount.iterations++;
                    break;
                case SEL_STAT:
                case SEL_CLAUSE:
                    typeCount.selections++;
                    break;
                case FUNC_DEF:
                    typeCount.functionDefinitions++;
                    break;
                default:
                    typeCount.other++;
                    break;
            }
        }
        return typeCount;
    }

    private int match(Vertex vertexA, CDG CDG_A, Vertex vertexB, CDG CDG_B, CDG pairedVertex) {
        int result = 0;
        // dynamic programming
        if (vertexA.getType() == vertexB.getType()) {
            ArrayList<Vertex> childrenOfA = CDG_A.getChildrenOf(vertexA);
            ArrayList<Vertex> childrenOfB = CDG_B.getChildrenOf(vertexB);
            int childrenNumOfA = childrenOfA.size();
            int childrenNumOfB = childrenOfB.size();
            int[][] M = new int[childrenNumOfA+1][childrenNumOfB+1];
            int[][] W = new int[childrenNumOfA+1][childrenNumOfB+1];

            for (int i = 0; i <= childrenNumOfA; i++) M[i][0] = 0;
            for (int j = 0; j <= childrenNumOfB; j++) M[0][j] = 0;

            for (int i = 1; i <= childrenNumOfA; i++) {

                for (int j = 1; j <= childrenNumOfB; j++) {
                    Vertex i_childOfA = childrenOfA.get(i-1);
                    Vertex j_childOfB = childrenOfB.get(j-1);
                    W[i][j] = match(i_childOfA, CDG_A, j_childOfB, CDG_B, pairedVertex);
                    M[i][j] = max(M[i][j-1], M[i-1][j], M[i-1][j-1]+W[i][j]);

                    if (M[i][j] > M[i - 1][j - 1] && M[i][j] > M[i - 1][j] && M[i][j] > M[i][j - 1])
                        pairedVertex.addVertex(j_childOfB);
                }
            }

            result = M[childrenNumOfA][childrenNumOfB]+1;
        }
        return result;
    }

    private int max(int a, int b, int c) {
        return max(a, max(b, c));
    }

    private int max(int a, int b) {
        return a >= b ? a : b;
    }
}
