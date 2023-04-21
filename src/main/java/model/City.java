package model;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class City {
    private int city_id;
    private String city_name;

    public City(int city_id, String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }

    public City(int city_id) {
        this.city_id = city_id;
    }
}

