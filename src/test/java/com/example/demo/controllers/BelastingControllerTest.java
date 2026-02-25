package com.example.demo.controllers;

import com.example.demo.models.Belasting;
import com.example.demo.services.BelastingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BelastingControllerTest {
    @InjectMocks
    BelastingController belastingController;

    @Mock
    BelastingService belastingService;

    @Test
    public void NieuweInkomenBelastingTest(){ // Inkomen belasting bestaat al een
        Mockito.when(belastingService.BestaatAlInkomenBelasting(anyLong(),anyInt()))
               .thenReturn(true);

        IllegalStateException exception=assertThrows(IllegalStateException.class,()->
        System.out.println(belastingController.NieuweInkomenBelasting(111111111L,200000.0,2026)));

      assertEquals("Er bestaat al een belastingaangifte",exception.getMessage());

    }


    @Test
    public void NieuweInkomenBelastingTestTwee(){ // NieuweInkomenBelasting toevoegen
       List<Belasting> list=new ArrayList<>();
       list.add(new Belasting(111L,"inkomen",2026,200000.0,2000.0));

        Mockito.when(belastingService.BestaatAlInkomenBelasting(anyLong(),anyInt()))
                .thenReturn(false);
        Mockito.when(belastingService.NieuweInkomenBelastingToevoegen(111L,200000.0,2026))
                .thenReturn(list);

        assertEquals(list,belastingController.NieuweInkomenBelasting(111L,200000.0,2026));

    }

}
