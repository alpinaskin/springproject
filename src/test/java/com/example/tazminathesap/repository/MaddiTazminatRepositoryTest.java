package com.example.tazminathesap.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.tazminathesap.model.MaddiTazminat;
import com.example.tazminathesap.model.PasifDevreHesabi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MaddiTazminatRepositoryTest {
    @Autowired
    private MaddiTazminatRepository maddiTazminatRepository;
    @Autowired
    private PasifDevreHesabiRepository pasifDevreHesabiRepository;

    private MaddiTazminat maddiTazminat;

    @BeforeEach
    public void setUp()
    {
        maddiTazminat = new MaddiTazminat();
    }

    @AfterEach
    public void tearDown() {
        maddiTazminatRepository.deleteAll();
        maddiTazminat = null;
    }

    @Test
    public void givenMaddiTazminatToAddShouldReturnAddedMaddiTazminat(){
        maddiTazminat = maddiTazminatRepository.save(maddiTazminat);
        MaddiTazminat fetchedMaddiTazminat = maddiTazminatRepository.findById(maddiTazminat.getId()).get();
        assertEquals(2, fetchedMaddiTazminat.getId());
    }

    @Test
    public void givenGetAllMaddiTazminatShouldReturnListOfAllMaddiTazminat(){
        MaddiTazminat maddiTazminat1 = new MaddiTazminat();
        maddiTazminat1.setPasifDevreHesabi(pasifDevreHesabiRepository.save(new PasifDevreHesabi(LocalDate.of(2012,02,05),LocalDate.of(2013,05,13),new BigDecimal(2500),new BigDecimal(4000))));


        System.out.println(maddiTazminat1.getPasifDevreHesabi().getSonCalismaTarihi());

        MaddiTazminat maddiTazminat2 = new MaddiTazminat();
        maddiTazminat2.setPasifDevreHesabi(pasifDevreHesabiRepository.save(new PasifDevreHesabi()));

        System.out.println(pasifDevreHesabiRepository.findAll());
        

        maddiTazminatRepository.save(maddiTazminat1);
        maddiTazminatRepository.save(maddiTazminat2);
        List<MaddiTazminat> maddiTazminatList = (List<MaddiTazminat>) maddiTazminatRepository.findAll();
        assertEquals(LocalDate.of(2012,02,05), maddiTazminatList.get(0).getPasifDevreHesabi().getSonCalismaTarihi());
   }
   @Test
    public void givenIdTODeleteThenShouldDeleteTheMaddiTazminat() {
        MaddiTazminat maddiTazminat1 = new MaddiTazminat();
        maddiTazminatRepository.save(maddiTazminat1);
        maddiTazminatRepository.deleteById(maddiTazminat1.getId());
        Optional<MaddiTazminat> optional = maddiTazminatRepository.findById(maddiTazminat1.getId());
        assertEquals(Optional.empty(), optional);
    }

    @Test
    public void updateMaddiTazminatShouldUpdateMaddiTazminat(){
        MaddiTazminat maddiTazminat1 = new MaddiTazminat();
        maddiTazminat1 = maddiTazminatRepository.save(maddiTazminat1);

        MaddiTazminat maddiTazminat2 = new MaddiTazminat();
        maddiTazminat2.setPasifDevreHesabi((PasifDevreHesabi) pasifDevreHesabiRepository.save(new PasifDevreHesabi()));

        maddiTazminat2.setId(maddiTazminatRepository.getById(maddiTazminat1.getId()).getId());
        maddiTazminatRepository.save(maddiTazminat2);
        assertNotNull(maddiTazminatRepository.getById(maddiTazminat1.getId()).getPasifDevreHesabi());
    }
}
