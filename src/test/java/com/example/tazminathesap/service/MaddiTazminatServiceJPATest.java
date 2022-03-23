package com.example.tazminathesap.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.tazminathesap.model.GecmisDevreHesabi;
import com.example.tazminathesap.model.GelecekDevreHesabi;
import com.example.tazminathesap.model.MaddiTazminat;
import com.example.tazminathesap.model.PasifDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.repository.MaddiTazminatRepository;
import com.example.tazminathesap.service.jpa.MaddiTazminatServiceJPA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MaddiTazminatServiceJPATest {

    @Mock
    private MaddiTazminatRepository maddiTazminatRepository;

    @Autowired
    @InjectMocks
    private MaddiTazminatServiceJPA maddiTazminatService;
    private MaddiTazminat maddiTazminat1;
    private MaddiTazminat maddiTazminat2;
    List<MaddiTazminat> maddiTazminatList;

    @BeforeEach
    public void setUp(){
        maddiTazminatList = new ArrayList<>();

        maddiTazminat1 = new MaddiTazminat(new TazminatRapor(), new GecmisDevreHesabi(), new GelecekDevreHesabi(), new PasifDevreHesabi());
        maddiTazminat1.setId(1L);
        maddiTazminat2 = new MaddiTazminat();
        maddiTazminat2.setId(2L);

        maddiTazminatList.add(maddiTazminat1);
        maddiTazminatList.add(maddiTazminat2);
    }

    @AfterEach
    public void tearDown(){
        maddiTazminat1 = maddiTazminat2 = null;
        maddiTazminatList = null;
    }

    @Test
    public void givenMaddiTazminatSaveAndReturnMaddiTazminat(){
        //given
        //then
        
        when(maddiTazminatRepository.save(any())).thenReturn(maddiTazminat1);
        maddiTazminatService.save(maddiTazminat1);
        verify(maddiTazminatRepository, times(1)).save(any());
    }

    @Test
    public void givenGetAllMaddiTazminatShouldReturnListOfMaddiTazminat(){
        //given
        maddiTazminatRepository.save(maddiTazminat1);
        maddiTazminatRepository.save(maddiTazminat2);
        //then
        //when
        when(maddiTazminatRepository.findAll()).thenReturn(maddiTazminatList);
        List<MaddiTazminat> maddiTazminatList1 = maddiTazminatService.findAll();
        assertEquals(maddiTazminatList1, maddiTazminatList);
        verify(maddiTazminatRepository, times(1)).save(maddiTazminat1);
        verify(maddiTazminatRepository, times(1)).save(maddiTazminat2);
        verify(maddiTazminatRepository, times(1)).findAll();
    }

    @Test
    public void givenIdShouldReturnMaddiTazminat() {
        when(maddiTazminatRepository.findById(1L)).thenReturn(Optional.ofNullable(maddiTazminat1));
        assertThat(maddiTazminatService.findById(1L)).isEqualTo(maddiTazminat1);
    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheMaddiTazminat(){
        
        //given
        //then
        //when
        maddiTazminatService.deleteById(maddiTazminat1.getId());
        verify(maddiTazminatRepository).deleteById(any());


    }
}
