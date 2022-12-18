package com.quad.TaxHaven.domain.user;

import com.quad.TaxHaven.domain.plan.Plan;
import com.quad.TaxHaven.domain.util.IncomeType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Client extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String aadhaar;
    private String panNumber;
    private List<IncomeType> incomeTypeList;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Plan> planList;
    private Boolean isClientActive;
}
