package org.workintech.entity.user;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.workintech.entity.Product;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cart_item", schema = "ecommerce")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "is_checked")
    private Boolean isChecked;

    @Column(name = "is_active")
    private Boolean isActive;
}
