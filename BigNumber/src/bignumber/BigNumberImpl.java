package bignumber;

import java.util.Stack;

public class BigNumberImpl implements BigNumber {

    private Node head;

    // Inner class for the linked list nodes
    private static class Node {
        int digit;
        Node next;

        Node(int digit) {
            this.digit = digit;
            this.next = null;
        }
    }
    public BigNumberImpl() {
        // Constructor that creates the number 0
        this.head = new Node(0);
    }
    public BigNumberImpl(String number) {
        // Check if the provided string is null or doesn't represent a valid non-negative number
        if (number == null || !number.matches("[0-9]+")) {
            throw new IllegalArgumentException("Invalid number format.");
        }

        // Reverse the string for easy insertion in the linked list
        number = new StringBuilder(number).reverse().toString();

        // Create the head of the linked list with the least significant digit (after the string is reversed)
        this.head = new Node(Character.digit(number.charAt(0), 10));

        // Start building the linked list from the head
        Node current = head;

        // Iterate over the remaining characters in the reversed string
        for (int i = 1; i < number.length(); i++) {
            // Create a new node for each digit and append it to the linked list
//            int digit = Character.digit("1234566".charAt(0), 10); // digit will be 1
            current.next = new Node(Character.digit(number.charAt(i), 10));

            // Move to the next node in the list
            current = current.next;
        }
    }

    @Override
    public int length() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.digit);
            current = current.next;
        }
        // Reverse to get the correct order
        String result = sb.reverse().toString();

        // Remove leading zeros, but keep at least one digit
        if (result.length() > 1) {
            result = result.replaceFirst("^0+(?!$)", "");
        }

        return result;
    }

    @Override
    public void shiftLeft(int shifts) {
        if (shifts < 0) {
            shiftRight(-shifts); // Delegate to shiftRight for negative shifts
            return;
        }

        for (int i = 0; i < shifts; i++) {
            Node newNode = new Node(0); // new node with digit 0
            newNode.next = head; // link new node to the head
            head = newNode; // update head to be the new node
        }
    }

    @Override
    public void shiftRight(int shifts) {
        if (shifts < 0) {
            shiftLeft(-shifts); // Delegate to shiftLeft for negative shifts
            return;
        }

        for (int i = 0; i < shifts; i++) {
            if (head == null || head.next == null) {
                head = new Node(0); // if list is empty or has one node, result is 0
                break;
            }
            head = head.next; // move head to the next node
        }
    }
    @Override
    public void addDigit(int digit) throws IllegalArgumentException {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit must be between 0 and 9");
        }
        if (head.digit == 0 && head.next == null) {
            // If the current BigNumber is 0, replace the head with the new digit.
            head.digit = digit;
        } else {
            // Otherwise, proceed with adding the digit normally.
            Node current = head;
            int carry = digit;
            while (current != null && carry > 0) {
                int sum = current.digit + carry;
                current.digit = sum % 10;
                carry = sum / 10;
                if (current.next == null && carry > 0) {
                    current.next = new Node(carry);
                    break;
                }
                current = current.next;
            }
        }
    }



    @Override
    public int getDigitAt(int position) throws IllegalArgumentException {
        if (position < 0) {
            throw new IllegalArgumentException("Position must be non-negative");
        }
        Node current = head;
        while (position-- > 0 && current != null) {
            current = current.next;
        }
        if (current == null) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        return current.digit;
    }


    @Override
    public BigNumber copy() {
        BigNumberImpl copy = new BigNumberImpl();
        Node current = this.head;
        Node copyCurrent = copy.head = new Node(current.digit);
        while (current.next != null) {
            copyCurrent.next = new Node(current.next.digit);
            current = current.next;
            copyCurrent = copyCurrent.next;
        }
        return copy;
    }
    @Override
    public BigNumber add(BigNumber other) {
        if (!(other instanceof BigNumberImpl)) {
            throw new IllegalArgumentException("The object to add must be an instance of BigNumberImpl");
        }
        BigNumberImpl otherNumber = (BigNumberImpl) other;
        BigNumberImpl result = new BigNumberImpl("0"); // Start with zero to handle the head correctly.
        Node thisCurrent = this.head;
        Node otherCurrent = otherNumber.head;
        Node resultCurrent = result.head;
        Node prevNode = null; // To keep track of the last non-zero node.

        int carry = 0;
        while (thisCurrent != null || otherCurrent != null || carry != 0) {
            int sum = carry;
            if (thisCurrent != null) {
                sum += thisCurrent.digit;
                thisCurrent = thisCurrent.next;
            }
            if (otherCurrent != null) {
                sum += otherCurrent.digit;
                otherCurrent = otherCurrent.next;
            }
            resultCurrent.digit = sum % 10; // Set the digit for the current node.
            carry = sum / 10;

            if (resultCurrent.digit != 0 || carry != 0) {
                prevNode = resultCurrent; // Update the last non-zero node.
            }

            if (thisCurrent != null || otherCurrent != null || carry != 0) {
                resultCurrent.next = new Node(0); // Only create a new node if necessary.
                resultCurrent = resultCurrent.next;
            }
        }

        if (prevNode != null && prevNode.next != null && prevNode.next.digit == 0) {
            prevNode.next = null; // Remove any trailing zero nodes.
        }

        return result;
    }

    private int getSize(Node node) {
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    @Override
    public int compareTo(BigNumber other) {
        if (!(other instanceof BigNumberImpl)) {
            throw new IllegalArgumentException("The object to compare must be an instance of BigNumberImpl");
        }
        BigNumberImpl otherNumber = (BigNumberImpl) other;

        int thisSize = getSize(this.head);
        int otherSize = getSize(otherNumber.head);

        if (thisSize != otherSize) {
            return thisSize > otherSize ? 1 : -1;
        }

        // Use stacks to reverse the order for comparison if numbers are stored in reverse
        Stack<Integer> thisStack = new Stack<>();
        Stack<Integer> otherStack = new Stack<>();

        Node thisCurrent = this.head;
        Node otherCurrent = otherNumber.head;

        // Push all digits to the stacks
        while (thisCurrent != null) {
            thisStack.push(thisCurrent.digit);
            thisCurrent = thisCurrent.next;
        }
        while (otherCurrent != null) {
            otherStack.push(otherCurrent.digit);
            otherCurrent = otherCurrent.next;
        }

        // Compare using the stacks
        while (!thisStack.empty() && !otherStack.empty()) {
            int thisDigit = thisStack.pop();
            int otherDigit = otherStack.pop();
            if (thisDigit != otherDigit) {
                return thisDigit > otherDigit ? 1 : -1;
            }
        }

        return 0;
    }




}