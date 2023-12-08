package org.example.Solution.day08.model;

import org.example.Solution.utils.LongUtils;
import org.example.Solution.utils.RegexUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.example.Solution.day08.model.TraverseCommand.LEFT;
import static org.example.Solution.utils.StringUtils.toCharacterList;

public class NetworkExecutor {
    private static final String START_NODE_NAME = "AAA";
    private static final String START_NODE_CHAR = "A";
    private static final String END_NODE_NAME = "ZZZ";
    private static final String END_NODE_CHAR = "Z";

    private final List<NetworkNode> nodes;
    private final List<TraverseCommand> traverseCommands;
    private final Map<String, NetworkNode> lookupMap;

    public Long execute() {
        return execute(this::isNotEndingNode, findNodeWithName(START_NODE_NAME));
    }

    //Since every defined starting node is cyclic in such a way that it contains exactly one end-node its possible to
    //find the desired state where all 6 end on an end note by finding the least_common_multiple over the result of all 6;
    public Long executeGhost() {
        List<NetworkNode> currentNodes = findNodesEndingWithStartingCharacter();
        return currentNodes.stream()
                .map(n -> execute(this::doesNotEndWithEndCharacter, n))
                .reduce(1L, LongUtils::least_common_multiple);
    }

    private Long execute(Function<NetworkNode, Boolean> stopFunction, NetworkNode startNode) {
        long iterations = 0;
        var currentNode = startNode;
        while (stopFunction.apply(currentNode)) {
            var command = getTraverseCommand(iterations);
            currentNode = getChildForCommand(currentNode, command);
            iterations++;
        }
        return iterations;
    }

    private NetworkNode getChildForCommand(NetworkNode parent, TraverseCommand command) {
        return command.equals(LEFT) ? findNodeWithName(parent.left) : findNodeWithName(parent.right);
    }

    private boolean doesNotEndWithEndCharacter(NetworkNode node) {
        return !node.name.endsWith(END_NODE_CHAR);
    }

    private boolean isNotEndingNode(NetworkNode currentNode) {
        return !currentNode.name.equals(END_NODE_NAME);
    }

    private List<NetworkNode> findNodesEndingWithStartingCharacter() {
        return nodes.stream().filter(n -> hasFinalCharacter(n.name)).toList();
    }

    private boolean hasFinalCharacter(String str) {
        return str.endsWith(NetworkExecutor.START_NODE_CHAR);
    }

    private TraverseCommand getTraverseCommand(long index) {
        return traverseCommands.get((int) index % traverseCommands.size());
    }

    private NetworkNode findNodeWithName(String name) {
        if (!lookupMap.containsKey(name)) {
            throw new IllegalArgumentException(name + " does not exist in lookup map");
        }
        return lookupMap.get(name);
    }

    public NetworkExecutor(List<String> lines) {
        traverseCommands = toCharacterList(lines.get(0)).stream()
                .map(TraverseCommand::fromString)
                .toList();

        nodes = Arrays.stream(lines.get(1).split(RegexUtils.WINDOWS_NEWLINE))
                .map(NetworkNode::new)
                .toList();
        lookupMap = nodes.stream().collect(Collectors.toMap(a -> a.name, b -> b));
    }

}
