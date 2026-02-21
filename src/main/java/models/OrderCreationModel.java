package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreationModel {
    private String firstName = "Vfafa";
    private String lastName = "asdas";
    private String addres = "ASddc, 4";
    private String metroStation = "Liver";
    private String phone = "12356752";
    private String rentTime = "2";
    private String deliveryDate = "2026-10-10";
    private String comment = "Pomogite, ya tupoi";
    private List<String> color;

    public OrderCreationModel(List<String> color) {
        this.color = color;
    }
}
