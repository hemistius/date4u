package de.materna.trainee.date4u.db.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NamedEntityGraphs({
        @NamedEntityGraph(name = "profile.all", includeAllAttributes = true)
})
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private LocalDate birthdate;
    private short hornlength;
    private byte gender;
    @Column(name = "attracted_to_gender")
    private Byte attractedToGender;
    private String description;
    @Column(name = "lastseen")
    private LocalDateTime lastSeen;
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Photo> photos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return new EqualsBuilder().append(getId(), profile.getId()).append(getNickname(), profile.getNickname()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).append(getNickname()).toHashCode();
    }
}
