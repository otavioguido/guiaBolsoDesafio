package com.osilva.guiabolso.challenge.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class Transaction {

    private String description;

    private Long date;

    private Integer value;

    @Setter
    private Boolean duplicate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return description.equals(that.description) &&
                date.equals(that.date) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, date, value);
    }
}
