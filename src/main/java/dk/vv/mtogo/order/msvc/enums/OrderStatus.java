package dk.vv.mtogo.order.msvc.enums;

public enum OrderStatus {

    ERROR(0),
    RECEIVED(1),
    ACCEPTED(2),
    FINISHED(3),
    DENIED(4);

    private int _value;

    OrderStatus(int Value) {
        this._value = Value;
    }

    public int value() {
        return _value;
    }

    public static OrderStatus fromInt(int i) {
        for (OrderStatus b : OrderStatus.values()) {
            if (b.value() == i) { return b; }
        }
        return null;
    }

}
