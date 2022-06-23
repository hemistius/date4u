package de.materna.trainee.date4u.db.entities;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(LikesKey.class)
public class Likes {

    @OneToOne
    @JoinColumn(name = "LIKER_FK")
    @Id
    private Profile liker;

    @OneToOne
    @JoinColumn(name = "LIKEE_FK")
    @Id
    private Profile likee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Likes likes = (Likes) o;

        return new EqualsBuilder().append(getLiker(), likes.getLiker()).append(getLikee(), likes.getLikee()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getLiker()).append(getLikee()).toHashCode();
    }
}
