package uz.example.billing_service.Enums;

public enum OrderStatusEnum {
    PENDING(1),
    PAID(2),
    FAILED(3),
    REFUNDED(4);

    private final short code;

    OrderStatusEnum(int code) {
        this.code = (short) code;
    }

    public short getStatusCode() {
        return code;
    }

    public static OrderStatusEnum fromCode(int code) {
        for (OrderStatusEnum s : values()) {
            if (s.code == code) return s;
        }

        throw new IllegalArgumentException("Неизвестный статус: " + code);
    }
}
