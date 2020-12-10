package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Spy
    PetMapService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    /*@BeforeEach
    void setUp() {
        petService = spy(PetMapService.class);
        visitService = new VisitMapService();

        visitController = new VisitController(visitService,petService);
    }*/

    @Test
    void loadPetWithVisit() {
        //given
        Pet pet = new Pet(1l);
        Map<String, Object> model = new HashMap<>();
        petService.save(pet);
        //when(petService.findById(1l)).thenCallRealMethod();

        //when
        Visit visit = visitController.loadPetWithVisit(1l, model);

        //then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(1l);
        verify(petService).findById(anyLong());

    }


}