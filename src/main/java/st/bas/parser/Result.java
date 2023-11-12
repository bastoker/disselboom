package st.bas.parser;

import st.bas.func.Functor;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public abstract class Result<T> implements Functor<T> {
    public abstract T result();

    @Override
    public abstract <U> Result<U> map(Function<? super T, ? extends U> mapper);

    public <U> Result<U> mapFailure() {
        if (isFailure()) {
            return map(null);
        } else {
            throw new IllegalStateException("Only applicable to failures");
        }
    }

    boolean isSuccess()  {
        return this instanceof Result.Success<T>;
    }

    public abstract String getFailureMessage();

    public boolean isFailure()  {
        return this instanceof Result.Failure;
    }

    public static <T> Result<T> of(T result) {
        return new Success<>(result);
    }

    public static <T> Result<T> failure(String message) {
        return new Failure<>(message);
    }

    static class Success<T> extends Result<T> {
        private final T value;

        private Success(T value) {
            this.value = value;
        }

        public static <T> Result<T> success(T value) {
            return new Success<>(value);
        }

        @Override
        public T result() {
            return value;
        }

        @Override
        public <U> Result<U> map(Function<? super T, ? extends U> mapper) {
            return success(mapper.apply(value));
        }

        @Override
        public String getFailureMessage() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Success<?> success = (Success<?>) o;
            return Objects.equals(value, success.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Success{" +
                    "value=" + value +
                    '}';
        }
    }

    static class Failure<T> extends Result<T> {
        private final String message;

        private Failure(String message) {
            this.message = message;
        }

        public static <T> Result<T> failure(String message) {
            return new Failure<>(message);
        }

        @Override
        public T result() {
            throw new NoSuchElementException("No value present");
        }

        public String failureMessage() {
            return message;
        }

        @Override
        public String getFailureMessage() {
            return message;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public <U> Result<U> map(Function<? super T, ? extends U> mapper) {
            // Casting is eligible because failure values are compatible with all type parameters of Result
            @SuppressWarnings("unchecked")
            final Failure<U> failure = (Failure<U>) this;
            return failure;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Failure<?> failure = (Failure<?>) o;
            return Objects.equals(message, failure.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(message);
        }

        @Override
        public String toString() {
            return "Failure{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }
}
