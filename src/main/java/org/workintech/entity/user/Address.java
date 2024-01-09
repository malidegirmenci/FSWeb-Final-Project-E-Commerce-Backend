package org.workintech.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "address", schema = "ecommerce")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Address title must not be null, empty or blank")
    @Size(min = 3,max = 35,message = "Address title must not be less than 3 and greater than 35 characters.")
    @Column(name = "address_title")
    private String addressTitle;

    @NotBlank(message = "Name must not be null, empty or blank")
    @Size(min = 3,max = 35,message = "Name must not be less than 3 and greater than 35 characters.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Surname title must not be null, empty or blank")
    @Size(min = 3,max = 35,message = "Surname must not be less than 3 and greater than 35 characters.")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Phone must not be null, empty or blank")
    @Size(min = 10,max = 20,message = "Phone must not be less than 10 and greater than 20 characters.")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "City must not be null, empty or blank")
    @Size(min = 2,max = 50,message = "City must not be less than 2 and greater than 50 characters.")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "District must not be null, empty or blank")
    @Size(min = 2,max = 50,message = "District must not be less than 2 and greater than 50 characters.")
    @Column(name = "district")
    private String district;

    @NotBlank(message = "Neighborhood must not be null, empty or blank")
    @Size(min = 2,max = 50,message = "Neighborhood must not be less than 2 and greater than 50 characters.")
    @Column(name = "neighborhood")
    private String neighborhood;

    @NotBlank(message = "Address detail must not be null, empty or blank")
    @Size(min = 2,max = 200,message = "Address detail must not be less than 2 and greater than 200 characters.")
    @Column(name = "address_detail")
    private String addressDetail;

    @NotBlank(message = "Address detail must not be null, empty or blank")
    @Size(min = 2,max = 100,message = "Address detail must not be less than 2 and greater than 200 characters.")
    @Column(name = "token")
    private String token;

}
