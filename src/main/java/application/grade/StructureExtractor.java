package application.grade;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;

import java.util.ArrayList;
import java.util.Stack;

import static graph.VertexType.*;

public class StructureExtractor {
    private CDG inputCDG;
    private CDG outputCDG;
    private Stack<Vertex> vertexStack;
    private boolean seenNormalNode;

    public StructureExtractor(CDG inputCDG) {
        this.inputCDG = inputCDG;
        outputCDG = new CDG();
        vertexStack = new Stack<>();
        seenNormalNode = false;
        Vertex head = inputCDG.getHead();
        extractFrom(head);
    }

    public CDG get() {
        return outputCDG;
    }

    private void extractFrom(Vertex vertex) {
        if (isHead(vertex))
            handleHead(vertex);
        else if (isTerminal(vertex))
            handleTerminal(vertex);
        else
            handleNonTerminal(vertex);
    }

    private boolean isHead(Vertex vertex) {
        return inputCDG.isHead(vertex);
    }

    private void handleHead(Vertex head) {
        vertexStack.add(addHead());
        handleChildrenOf(head);
        if (seenNormalNode) {
            addNormalNodeAndEdge();
        }
        vertexStack.pop();
    }

    private Vertex addHead() {
        Vertex newHead = new Vertex(HEAD);
        outputCDG.addVertex(newHead);
        return newHead;
    }

    private void handleChildrenOf(Vertex vertex) {
        ArrayList<Vertex> children = inputCDG.getChildrenOf(vertex);
        for (Vertex child: children)
            extractFrom(child);
    }

    private void addNormalNodeAndEdge() {
        addNodeAndEdge(NODE);
    }

    private boolean isTerminal(Vertex vertex) {
        return inputCDG.isTerminal(vertex);
    }

    private void handleTerminal(Vertex vertex) {
        if (isOtherType(vertex.getType()))
            seenNormalNode = true;
        else{
            if (seenNormalNode) addNormalNodeAndEdge();
            addNodeAndEdge(vertex.getType());
            seenNormalNode = false;
        }

    }

    private Vertex addNodeAndEdge(VertexType type) {
        Vertex newNode = new Vertex(type);
        outputCDG.addVertex(newNode);
        outputCDG.addEdge(vertexStack.peek(), newNode);
        return newNode;
    }

    /*
     * Since non-terminal node can not be otherType
     * There is no need to do the redundant check
     * It is not rigorous though
     * */
    private void handleNonTerminal(Vertex vertex) {
        if (seenNormalNode)
            addNormalNodeAndEdge();
        seenNormalNode = false;
        Vertex nonTerminalNode = addNodeAndEdge(vertex.getType());
        vertexStack.add(nonTerminalNode);
        handleChildrenOf(vertex);
        if (seenNormalNode)
            addNormalNodeAndEdge();
        vertexStack.pop();
        seenNormalNode = false;
    }
}
