package vitalitus.carsharingapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.net.URL;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE payments SET is_deleted=true where id = ?")
@SQLRestriction(value = "is_deleted=false")
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(nullable = false, length = 1000)
    private URL sessionUrl;
    @Column(nullable = false, unique = true)
    private String sessionId;
    @Column(nullable = false)
    private BigDecimal amountToPay;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted = false;

    public enum Status {
        PENDING,
        PAID
    }

    public enum Type {
        PAYMENT,
        FINE
    }
}
