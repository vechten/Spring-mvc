package hubert.shop.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Order {
    private UUID orderId;
    private List<UUID> products;
}
