package at.cgsit.train.java.wrapper;

import java.io.Serializable;

public class MessageWrapper<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String type;
    private final T payload;

    public MessageWrapper(String type, T payload) {
        this.type = type;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "MessageWrapper{type='" + type + "', payload=" + payload + "}";
    }
}
