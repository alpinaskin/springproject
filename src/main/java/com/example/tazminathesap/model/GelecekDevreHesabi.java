package com.example.tazminathesap.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class GelecekDevreHesabi extends BaseEntity{
    
    private BigDecimal aktifDevreToplami;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true )
    @JoinColumn(name = "gelecek_devre_hesabi_id")
    private List<ZararDonemi> zararDonemleri;

}
