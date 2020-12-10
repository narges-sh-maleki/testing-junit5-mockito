package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String REDIRECT_OWNERS = "redirect:/owners/";
    @InjectMocks
    OwnerController ownerController;

    @Mock
    OwnerService ownerService;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;


    @Test
    void processCreationForm_BindingResultNoError() {
        //given
        when(bindingResult.hasErrors()).thenReturn(false);
        Owner savedOwner = new Owner(5l, "Narges", "Maleki");
        Owner owner = new Owner(7l, "Narges", "Maleki");
        when(ownerService.save(any(Owner.class))).thenReturn(savedOwner);

        //when
        String result = ownerController.processCreationForm(owner, bindingResult);

        //then
        assertThat(result).isEqualTo(REDIRECT_OWNERS + savedOwner.getId().toString());
        verify(ownerService, times(1)).save(any());
    }

    @Test
    void processCreationForm_BindingResultHasError() {
        //given
        when(bindingResult.hasErrors()).thenReturn(true);
        Owner owner = new Owner(7l, "Narges", "Maleki");

        //when
        String result = ownerController.processCreationForm(owner, bindingResult);

        //then
        final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
        assertThat(result).isEqualTo(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
        verify(bindingResult).hasErrors();

    }

    @Test
    void processFindFormArgumentCaptor() {
        //given*********
        List<Owner> list = new ArrayList<>();
        Owner owner = new Owner(1l, "Narges", "Maleki");
        //inline ArgumentCaptor
        //final ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        //ArgumentCaptor annotation
        when(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).thenReturn(list);
        //inline mock creation
        Model model = mock(Model.class);

        //when*********
        ownerController.processFindForm(owner, bindingResult, model);

        //then*********
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%Maleki%");
    }

    @Test
    void processFindFormResultIsEmpty() {
        //given*********
        List<Owner> list = new ArrayList<>();
        Owner owner = new Owner(1l, "Narges", "Maleki");
        //inline ArgumentCaptor
        //final ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        //ArgumentCaptor annotation
        when(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).thenReturn(list);
        //inline mock creation
        Model model = mock(Model.class);

        //when*********
        String returnString = ownerController.processFindForm(owner, bindingResult, model);

        //then*********
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%Maleki%");
        assertThat(returnString).isEqualToIgnoringCase("owners/findOwners");
    }

    @Test
    void processFindFormResultIsOneItem() {
        //given*********
        List<Owner> list = new ArrayList<>();
        Owner owner = new Owner(1l, "Narges", "Maleki");
        list.add(owner);
        //inline ArgumentCaptor
        //final ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        //ArgumentCaptor annotation
        when(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).thenReturn(list);
        //inline mock creation
        Model model = mock(Model.class);

        //when*********
        String returnString = ownerController.processFindForm(owner, bindingResult, model);

        //then*********
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%Maleki%");
        assertThat(returnString).isEqualToIgnoringCase("redirect:/owners/" + owner.getId());
    }
}