package com.example.tazminathesap.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MaddiTazminat extends BaseEntity {


    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="tazminat_id")
    private TazminatRapor tazminatRapor;

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="gecmis_devre_hesabi_id")
    private GecmisDevreHesabi gecmisDevreHesabi;
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="gelecek_devre_id")
    private GelecekDevreHesabi gelecekDevreHesabi;

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="pasif_devre_hesabi_id")
    private PasifDevreHesabi pasifDevreHesabi;


    public MaddiTazminat(TazminatRapor tazminatRapor, GecmisDevreHesabi gecmisDevreHesabi, GelecekDevreHesabi gelecekDevreHesabi, PasifDevreHesabi pasifDevreHesabi) {
        this.tazminatRapor = tazminatRapor;
        this.gecmisDevreHesabi = gecmisDevreHesabi;
        this.gelecekDevreHesabi = gelecekDevreHesabi;
        this.pasifDevreHesabi = pasifDevreHesabi;
    }

    public MaddiTazminat() {
    }

    public TazminatRapor getTazminatRapor() {
        return this.tazminatRapor;
    }

    public void setTazminatRapor(TazminatRapor tazminatRapor) {
        this.tazminatRapor = tazminatRapor;
    }

    public GecmisDevreHesabi getGecmisDevreHesabi() {
        return this.gecmisDevreHesabi;
    }

    public void setGecmisDevreHesabi(GecmisDevreHesabi gecmisDevreHesabi) {
        this.gecmisDevreHesabi = gecmisDevreHesabi;
    }

    public GelecekDevreHesabi getGelecekDevreHesabi() {
        return this.gelecekDevreHesabi;
    }

    public void setGelecekDevreHesabi(GelecekDevreHesabi gelecekDevreHesabi) {
        this.gelecekDevreHesabi = gelecekDevreHesabi;
    }

    public PasifDevreHesabi getPasifDevreHesabi() {
        return this.pasifDevreHesabi;
    }

    public void setPasifDevreHesabi(PasifDevreHesabi pasifDevreHesabi) {
        this.pasifDevreHesabi = pasifDevreHesabi;
    }
    
}
