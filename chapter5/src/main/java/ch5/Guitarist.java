package ch5;

import ch2.common.Guitar;
import ch2.common.Singer;

public class Guitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("Just keep me where the light is");
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void sing2() {
        System.out.println("Oh gravity, stay the hell away from me");
    }

    public void rest() {
        System.out.println("zzz");
    }
}
