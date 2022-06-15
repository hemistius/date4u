package de.materna.trainee.date4u.db.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NamedEntityGraphs({
        @NamedEntityGraph(name = "unicorn.all", includeAllAttributes = true),
        @NamedEntityGraph(name = "unicorn.sparse", attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("email"),
                @NamedAttributeNode("password")
        })
})
public class Unicorn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @OneToOne
    @JoinColumn(name = "PROFILE_FK")
    private Profile profile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Unicorn unicorn = (Unicorn) o;

        return new EqualsBuilder().append(getId(), unicorn.getId()).append(getEmail(), unicorn.getEmail()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).append(getEmail()).toHashCode();
    }
}
