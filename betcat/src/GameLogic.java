public class GameLogic {

    public static final String RED_BOLD = "\033[1;31m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String GREEN_BOLD = "\033[1;32m";

    private ServerTry server;
    private ClientConnection player;
    private Horse[] horses;
    private Horse winner;

    public GameLogic(ClientConnection player, ServerTry server) {
        this.server = server;
        this.horses = new Horse[4];
        this.player = player;
    }

    public void start() {
        createHorses();
        run();
    }

    public void createHorses() {
        horses[0] = new Horse("Mustang");
        horses[1] = new Horse("Speedy");
        horses[2] = new Horse("Lightning");
        horses[3] = new Horse("Zeus");
    }

    public Horse getWinnerHorse() {
        winner = horses[NumberRandomizer.normalRandom()];
        return winner;
    }

    public synchronized void run() {
        server.setWinnerHorse(getWinnerHorse().toString());

        server.addBet();

        if (player.getPicked().equals(server.getWinnerHorse())) {
            server.addWinner();
            player.setCredits(deliverWinnings());
            player.send("\n" + YELLOW_BOLD + player.getName() + RED_BOLD + " won and has now " + YELLOW_BOLD +
                    player.getCredits() + " credits" + "! 💸");

        } else {
            player.send("\n" + YELLOW_BOLD + player.getName() + RED_BOLD + " lost " + YELLOW_BOLD + player.getBet() +
                    RED_BOLD + " credits! 🤯" +
                    "\n" + RED_BOLD + "Better luck next time!" + "\n");

        }

        while (!server.isAllBetsArePlaced()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public synchronized int deliverWinnings() {
        return server.deliverPrizePool(server.getWinners());
    }
}

