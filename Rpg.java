// Updated esploraMappa method to choose movement direction instead of random movement

public void esploraMappa() {
    Scanner scanner = new Scanner(System.in);
    String input;

    while (true) {
        System.out.println("Choose a direction to move (W/A/S/D or up/down/left/right):");
        input = scanner.nextLine().toLowerCase();

        if (input.equals("w") || input.equals("up")) {
            if (canMoveUp()) {
                // move up logic
            } else {
                System.out.println("You can't move up!");
            }
        } else if (input.equals("s") || input.equals("down")) {
            if (canMoveDown()) {
                // move down logic
            } else {
                System.out.println("You can't move down!");
            }
        } else if (input.equals("a") || input.equals("left")) {
            if (canMoveLeft()) {
                // move left logic
            } else {
                System.out.println("You can't move left!");
            }
        } else if (input.equals("d") || input.equals("right")) {
            if (canMoveRight()) {
                // move right logic
            } else {
                System.out.println("You can't move right!");
            }
        } else {
            System.out.println("Invalid direction! Please enter W/A/S/D or up/down/left/right.");
        }

        // additional game logic unchanged
    }
}