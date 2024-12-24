package net.reactiveapp.reactiveservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("transaction")
public class Transaction {
    @Id
    private Long id;
    private String identifier;
    private LocalDateTime createdAt;
    private Double amount;
    private Long companyId;
}
