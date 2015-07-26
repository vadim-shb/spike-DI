package example_app;

import spike_di.annotation.Bean;

public class ContextConfig {

    @Bean("MessageGenerator")
    public MessageGenerator messageGenerator;

    @Bean("MessagePresenter")
    public MessagePresenter messagePresenter;
}
