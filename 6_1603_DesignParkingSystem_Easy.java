class ParkingSystem {
    private int bigSpaces;
    private int mediumSpaces;
    private int smallSpaces;

    public ParkingSystem(int big, int medium, int small) {
        bigSpaces = big;
        mediumSpaces = medium;
        smallSpaces = small;
    }

    public boolean addCar(int carType) {
        if (carType == 1 && bigSpaces > 0) {
            bigSpaces--;
            return true;
        }

        if (carType == 2 && mediumSpaces > 0) {
            mediumSpaces--;
            return true;
        }

        if (carType == 3 && smallSpaces > 0) {
            smallSpaces--;
            return true;
        }

        return false;
    }
}
