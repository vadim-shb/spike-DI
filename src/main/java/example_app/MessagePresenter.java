package example_app;

import spike_di.annotation.Executable;
import spike_di.annotation.Inject;

public class MessagePresenter {

    @Inject("MessageGenerator")
    MessageGenerator messageGenerator;

    @Executable
    public void presentMessage() {
        System.out.println("=============================");
        System.out.println(messageGenerator.generateMessage());
        System.out.println("=============================");
    }

}
