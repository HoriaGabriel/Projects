package library.validator;

public interface AppValidator<T> {

    void validate(T t);
}
