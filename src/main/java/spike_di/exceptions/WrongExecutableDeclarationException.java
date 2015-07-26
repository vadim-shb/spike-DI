package spike_di.exceptions;

public class WrongExecutableDeclarationException extends RuntimeException {
    public WrongExecutableDeclarationException() {
        super("Can not invoke @Executable method");
    }
}
