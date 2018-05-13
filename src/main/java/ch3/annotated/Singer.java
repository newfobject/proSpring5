package ch3.annotated;

import org.springframework.stereotype.Component;

@Component("johnMayer")
@Trophy(name = {"grammy", "platinum disk"})
public class Singer {

    private String lyric = "I played a quick game of chess " +
            "with the salt and pepper shaker";

    public void sing() {
        System.out.println(lyric);
    }
}
