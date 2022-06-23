package de.materna.trainee.date4u.db.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LikesKey implements Serializable {

    private Long liker;
    private Long likee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LikesKey likesKey = (LikesKey) o;

        return new EqualsBuilder().append(getLiker(), likesKey.getLiker()).append(getLikee(), likesKey.getLikee()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getLiker()).append(getLikee()).toHashCode();
    }
}
