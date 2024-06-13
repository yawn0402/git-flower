package org.example.flowerspringboot.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerList {
    private List<Flowers> items;
    private Long  flowerNum ;
}
