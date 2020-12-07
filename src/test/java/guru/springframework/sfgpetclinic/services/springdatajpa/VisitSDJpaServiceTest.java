package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepositoryMock;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    @Test
    void findAll() {
        //given
        Set<Visit> mockedObjectSet = new HashSet<>();
        Visit visit1 = new Visit(1l);
        Visit visit2 = new Visit(2l);
        mockedObjectSet.add(visit1);
        mockedObjectSet.add(visit2);
        when(visitRepositoryMock.findAll()).thenReturn(mockedObjectSet);

        //when
        Set<Visit> result = visitSDJpaService.findAll();

        //then
        //  assertThat(result).hasSize(2);
        // assertThat(result).contains(visit1);
        assertThat(result).contains(new Visit(1l));//it wont work because we havent implemented hash and equals
        verify(visitRepositoryMock, times(1)).findAll();
    }

    @Test
    void findById() {
        //given
        Visit mockedObjectReturnValue = new Visit(1l);
        when(visitRepositoryMock.findById(anyLong())).thenReturn(Optional.of(mockedObjectReturnValue));

        //when
        Visit foundVisit = visitSDJpaService.findById(1l);

        //then
        assertThat(foundVisit).isNotNull();
        verify(visitRepositoryMock, times(1)).findById(anyLong());

    }

    @Test
    void save() {
        //given
        Visit mockedObjectParam = new Visit(1l);
        //Visit mockedObjectParam = any(Visit.class);
        when(visitRepositoryMock.save(mockedObjectParam)).thenReturn(mockedObjectParam);

        //when
        Visit newVisit = visitSDJpaService.save(new Visit(1l));

        //then
        assertThat(newVisit).isNotNull();
        verify(visitRepositoryMock, times(1)).save(any(Visit.class));
    }

    @Test
    void delete() {
        //given
        Visit visit = new Visit(1l);

        //when
        visitSDJpaService.delete(visit);

        //then
        verify(visitRepositoryMock, times(1)).delete(any(Visit.class));

    }

    @Test
    void deleteById() {
        //given

        //when
        visitSDJpaService.deleteById(anyLong());

        //then
        verify(visitRepositoryMock, times(1)).deleteById(anyLong());
    }
}