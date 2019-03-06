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
    private boolean hadSeenNode;

    StructureExtractor(CDG inputCDG) {
        this.inputCDG = inputCDG;
        outputCDG = new CDG();
        vertexStack = new Stack<>();
        hadSeenNode = false;
        Vertex head = inputCDG.getHead();
        extractFrom(head);
    }

    public CDG get() {
        return outputCDG;
    }

    private void extractFrom(Vertex vertex) {
        if (isHead(vertex)) {
            addHeadAndPushToStack();
            extractChildrenOf(vertex);
            addNodeIfSeen();
            popStack();
        }
        else if (isTerminal(vertex))
            seenTerminalVertexHandler(vertex);
        else {
            addNodeIfSeen();
            addVertexAndEdgeAndPushToStack(vertex.getType());
            extractChildrenOf(vertex);
            addNodeIfSeen();
            popStack();
        }

    }

    private boolean isHead(Vertex vertex) {
        return inputCDG.isHead(vertex);
    }

    private void addHeadAndPushToStack() {
        vertexStack.add(addHead());
    }

    private Vertex addHead() {
        Vertex vertex = new Vertex(HEAD);
        outputCDG.addVertex(vertex);
        return vertex;
    }

    private void addNodeIfSeen() {
        if (hadSeenNode) {
            addNodeAndEdge();
            unSeenNode();
        }
    }

    private void popStack() {
        vertexStack.pop();
    }

    private void extractChildrenOf(Vertex vertex) {
        ArrayList<Vertex> children = inputCDG.getChildrenOf(vertex);
        for (Vertex child: children)
            extractFrom(child);
    }

    private void addNodeAndEdge() {
        addVertexAndEdge(NODE);
    }

    private boolean isTerminal(Vertex vertex) {
        return inputCDG.isTerminal(vertex);
    }

    private void seenTerminalVertexHandler(Vertex vertex) {
        if (isOtherType(vertex.getType()))
            seenNode();
        else{
            addNodeIfSeen();
            addVertexAndEdge(vertex.getType());
            unSeenNode();
        }
    }

    private void addVertexAndEdgeAndPushToStack(VertexType type) {
        vertexStack.push(addVertexAndEdge(type));
    }

    private Vertex addVertexAndEdge(VertexType type) {
        Vertex newNode = new Vertex(type);
        outputCDG.addVertex(newNode);
        outputCDG.addEdge(vertexStack.peek(), newNode);
        return newNode;
    }

    private void seenNode() {
        hadSeenNode = true;
    }

    private void unSeenNode() {
        hadSeenNode = false;
    }
}
