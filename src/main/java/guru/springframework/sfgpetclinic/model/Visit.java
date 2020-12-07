package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;

public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;
    private Pet pet;

    public Visit() {
        super(null);
    }

    public Visit(Long id) {
        super(id);
    }

    public Visit(Long id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit)) return false;

        Visit visit = (Visit) o;

        return  (getId().equals(visit.getId())) ;

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
