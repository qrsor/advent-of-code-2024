package pl.qrsor.adventofcode2024.day6;

class WalkingInCirclesException extends Exception {
    private Footstep currentFootstep;

    public WalkingInCirclesException(Footstep currentFootstep) {
        this.currentFootstep = currentFootstep;
    }

    public WalkingInCirclesException() {
    }

    public Footstep getCausingFootstep() {
        return currentFootstep;
    }
}
