package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.internal.verification.AtLeast;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @Mock(lenient = true)
    SpecialtyRepository specialtyRepository;


    @Test
    void delete() {

      //given
        Speciality speciality = any(Speciality.class);
        //when
        specialitySDJpaService.delete(speciality);
        //then
        verify(specialtyRepository,times(1 )).delete(any());

    }

    @Test
    void deleteById() {
        //given

        //when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        //then
        verify(specialtyRepository, times(2)).deleteById(1l);
        //atLeast, atMost
    }


    @Test
    void deleteByIdNever() {
        //given

        //when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        //then
        verify(specialtyRepository, never()).deleteById(5l);
        //atLeast, atMost
    }

    @Test
    void findById() {
        //given
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(any())).thenReturn(Optional.of(speciality));
        //doReturn(Optional.of(speciality)).when(specialtyRepository).findById()

        //when
        Speciality foundSpeciality = specialitySDJpaService.findById(any());

        //then
        assertThat(foundSpeciality).isNotNull();
        verify(specialtyRepository, times(1)).findById(any());
    }

    @Test
    void testThrowException() {

        //given
        Speciality speciality = new Speciality();
        doThrow(new RuntimeException()).when(specialtyRepository).delete(any(Speciality.class));


        //when & then
        assertThrows(RuntimeException.class,()->specialitySDJpaService.delete(speciality));
    }


    @Test
    void testSaveLambdaMatch() {
        //given
        final String MATCH_ME ="match_me";
        Speciality specialityParam = new Speciality(1l,MATCH_ME);

        Speciality savedSpecialty = new Speciality(1l,"match_me");
        given(specialtyRepository.save(argThat(i -> i.getDescription().matches(MATCH_ME)))).willReturn(savedSpecialty);
       // when(specialtyRepository.save(any())).thenReturn(savedSpecialty);

        //when
        Speciality returnSpeciality = specialitySDJpaService.save(specialityParam);
        //then
        assertThat(returnSpeciality.getId()).isEqualTo(1l);
        //assertNull(returnSpeciality);

    }

    @Test
    void testSaveLambdaNotMatch() {
        //given
        final String MATCH_ME ="match_me";
        Speciality specialityParam = new Speciality(1l,"not a match");

        Speciality savedSpecialty = new Speciality(1l,"match_me");
        given(specialtyRepository.save(argThat(i -> i.getDescription().matches(MATCH_ME)))).willReturn(savedSpecialty);
        // when(specialtyRepository.save(any())).thenReturn(savedSpecialty);

        //when
        Speciality returnSpeciality = specialitySDJpaService.save(specialityParam);
        //then
        assertNull(returnSpeciality);

    }
}