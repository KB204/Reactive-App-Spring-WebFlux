package net.reactiveapp.reactiveservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("company")
public class Company {
    @Id
    private Long id;
    private String name;
    private Double price;
}
