package org.example.Solution.day08.model;

import org.example.Solution.utils.RegexUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.Solution.day08.model.TraverseCommand.LEFT;
import static org.example.Solution.utils.StringUtils.toCharacterList;

public class NetworkExecutor {
    private static final String START_NODE_NAME = "AAA";
    private static final String END_NODE_NAME = "ZZZ";

    private final List<NetworkNode> nodes;
    private final List<TraverseCommand> traverseCommands;
    private final Map<String, NetworkNode> lookupMap;

    public Long execute(){
        long iterations = 0;
        var currentNode = findNodeWithName(START_NODE_NAME);
        while (!currentNode.name.equals(END_NODE_NAME)){
            var command = getTraverseCommand(iterations);
            currentNode = command.equals(LEFT) ? findNodeWithName(currentNode.left) : findNodeWithName(currentNode.right);
            iterations++;
        }
        return iterations;
    }

    private TraverseCommand getTraverseCommand(long index){
        return traverseCommands.get((int)index % traverseCommands.size());
    }

    private NetworkNode findNodeWithName(String name){
        if (!lookupMap.containsKey(name)){
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
