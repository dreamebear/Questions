package QuestionSetA;

/*
On an infinite plane, a robot initially stands at (0, 0) and faces north.

The robot can receive one of three instructions:
"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degrees to the right.

The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 */
public class LC1041_RobotBoundedInCircle {

    public static void main(String[] args) {

    }

    // solution 1
    private boolean isRobotBounded1(String instructions) {

        // iterate 4 times, check if back to [0,0]

        int[] position = {0,0};
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        int direction = 0;

        instructions += instructions;
        instructions += instructions;

        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'G') {
                position[0] += directions[direction][0];
                position[1] += directions[direction][1];
            } else if (instruction == 'R') {
                direction = (direction+1) % 4;
            } else if (instruction == 'L') {
                direction = (direction+3) % 4;
            }
        }
        return position[0] == 0 && position[1] == 0;
    }

    // solution 2
    private boolean isRobotBounded2(String instructions) {

        // 2 condition if is a circle:
        // 1. back to [0,0]
        // 2. in other position but not facing north

        int[] position = {0,0};
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        int direction = 0;

        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'G') {
                position[0] += directions[direction][0];
                position[1] += directions[direction][1];
            } else if (instruction == 'R') {
                direction = (direction+1) % 4;
            } else if (instruction == 'L') {
                direction = (direction+3) % 4;
            }
        }

        if (position[0] == 0 && position[1] == 0) {
            return true;
        } else {
            return direction != 0;
        }
    }

}
