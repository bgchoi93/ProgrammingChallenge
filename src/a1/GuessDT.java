package a1;

import java.util.*;

public class GuessDT {

    private static final String STACK = "stack";
    private static final String QUEUE = "queue";
    private static final String PRIORITYQUEUE = "priority queue";
    private static final String NOTSURE = "not sure";
    private static final String IMPOSSIBLE = "impossible";

    public static void main(String[] args) {

        GuessDT guessDT = new GuessDT();

        Scanner in = new Scanner(System.in);

        while(in.hasNext()) {
            int numOps = in.nextInt();

            // add operation sets (operation-number pair)
            List<OperationSet> operationSets = new ArrayList<>();
            for (int i = 0; i < numOps; i++) {
                int op = in.nextInt();
                int num = in.nextInt();
                operationSets.add(guessDT.constructOperationSet(op, num));
            }

            // guess data structure of the operation sets
            System.out.println(guessDataStructure(operationSets));
        }
    }

    /**
     * Get operation set from the given input
     * @param op operation 1 : IN  or 2 : OUT
     * @param num value of number to operation with
     * @return OperationSet object
     */
    OperationSet constructOperationSet(int op, int num) {
        return new OperationSet(op, num);
    }

    /**
     * Guess data structure with the given operation sets
     * @param operationSets list of operation sets
     * @return string indicating the result of the guess
     */
    private static String guessDataStructure(List<OperationSet> operationSets) {
        // Define data structures
        // Priority Queue (Max)
        Queue<Integer> priorityQueue = new PriorityQueue<>(10, Collections.reverseOrder());
        // Stack
        Stack<Integer> stack = new Stack<>();
        // Queue
        Queue<Integer> queue = new LinkedList<>();

        // Start with true values for the guess
        boolean isPriorityQueue = true;
        boolean isStack = true;
        boolean isQueue = true;

        for (OperationSet operationSet : operationSets) {
            // if operation is 1, input the number to each data structure
            if (operationSet.op == 1) {
                priorityQueue.add(operationSet.num);
                stack.push(operationSet.num);
                queue.add(operationSet.num);
            }
            // if operation is 2, pop/dequeue the element from the data structures and compare with the expected output
            else {
                if (!priorityQueue.isEmpty() && !queue.isEmpty() && !stack.isEmpty()) {
                    isPriorityQueue = isPriorityQueue && (priorityQueue.remove() == operationSet.num);
                    isStack = isStack && (stack.pop() == operationSet.num);
                    isQueue = isQueue && (queue.remove() == operationSet.num);
                }
                // data structures are empty - impossible to pop/dequeue from empty data structures
                else {
                    return IMPOSSIBLE;
                }
            }
        }

        // None of the guess is right - impossible
        if (!isPriorityQueue && !isQueue && !isStack) {
            return IMPOSSIBLE;
        }

        if (isPriorityQueue && !isQueue && !isStack) {
            return PRIORITYQUEUE;
        }

        if (!isPriorityQueue && isQueue && !isStack) {
            return QUEUE;
        }

        if (!isPriorityQueue && !isQueue && isStack) {
            return STACK;
        }

        // it be two or more data structures
        return NOTSURE;
    }

    /**
     * This class defines operation set : operation (IN/OUT) - value pair
     */
    class OperationSet {
        int op;
        int num;

        OperationSet(int op, int num) {
            if ((op != 1) && (op != 2)) {
                throw new IllegalArgumentException("Operation must be 1 or 2");
            }
            this.op = op;
            this.num = num;
        }
    }
}

