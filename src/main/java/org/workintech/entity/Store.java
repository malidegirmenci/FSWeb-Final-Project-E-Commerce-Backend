package org.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "store", schema = "ecommerce")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name must not be null, empty or blank")
    @Size(min = 3,max = 35,message = "Name must not be less than 3 and greater than 35 characters.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Phone must not be null, empty or blank")
    @Size(min = 10,max = 20,message = "Phone must not be less than 10 and greater than 20 characters.")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Tax number must not be null, empty or blank")
    @Size(min = 3,max = 50,message = "Tax number must not be less than 3 and greater than 50 characters.")
    @Column(name = "tax_number")
    private String taxNumber;

    @NotBlank(message = "Iban must not be null, empty or blank")
    @Size(min = 3,max = 50,message = "Iban must not be less than 3 and greater than 50 characters.")
    @Column(name = "iban")
    private String iban;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "store")
    private List<Product> products;

}
